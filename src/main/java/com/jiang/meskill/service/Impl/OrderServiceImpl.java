package com.jiang.meskill.service.Impl;

import com.jiang.meskill.dao.ItemDOMapper;
import com.jiang.meskill.dao.OrderDOMapper;
import com.jiang.meskill.dao.SequenceDOMapper;
import com.jiang.meskill.dataobject.OrderDO;
import com.jiang.meskill.dataobject.SequenceDO;
import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.error.EmBusinessError;
import com.jiang.meskill.service.ItemService;
import com.jiang.meskill.service.OrderService;
import com.jiang.meskill.service.UserService;
import com.jiang.meskill.service.model.ItemModel;
import com.jiang.meskill.service.model.OrderModel;
import com.jiang.meskill.service.model.UserModel;
import com.jiang.meskill.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.basic.BasicTreeUI;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author jiangs
 * @create 2022-04-14-12:55
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private ValidatorImpl validator;

    @Transactional
    @Override
    public OrderModel createOrder(Integer userId, Integer itemId,Integer promoId, Integer amount) throws BusinessException {
        //校验
        ItemModel itemModel = itemService.getByItemId(itemId);
        if(itemModel==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品信息不存在");
        }
        UserModel userModel = userService.getUserById(userId);
        if(userModel==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }
        if(amount<=0||amount > 99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "数量信息不正确");
        }

        if(promoId!=null){
            if(promoId!=itemModel.getPromoModel().getId()){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动信息不正确");
            }else if(itemModel.getPromoModel().getStatus() != 2){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "活动未开始");
            }
        }

        //两种：1.落单减库存 2. 支付减库存
        if(!itemService.decreaseStock(itemId, amount)){
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        //订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setPromoId(promoId);
        orderModel.setAmount(amount);
        orderModel.setItemPrice(itemModel.getPrice());
        if(promoId!=null){
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(BigDecimal.valueOf(amount)));
        orderModel.setId(generateOrderNo());

        OrderDO orderDO = convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        itemService.increaseSales(itemId, amount);
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //这里如果不用数据库生成就要考虑并发问题
    public String generateOrderNo(){
        StringBuilder stringBuilder = new StringBuilder();
        //订单号16位
        //前8位为年-月日
        LocalDateTime now = LocalDateTime.now();
        String s = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(s);


        //中间为6为自增序列
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        Integer currentValue = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue()+sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String s1 = String.valueOf(currentValue);
        for(int i = 0;i < 6 - s1.length();i++){
            stringBuilder.append("0");
        }
        stringBuilder.append(s1);


        //最后两位为分库分表位
        stringBuilder.append("00");
        return stringBuilder.toString();
    }

    public OrderDO convertFromOrderModel(OrderModel orderModel){
        if(orderModel==null){
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        return orderDO;
    }

}
