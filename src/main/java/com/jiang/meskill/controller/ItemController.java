package com.jiang.meskill.controller;

import com.jiang.meskill.controller.VO.ItemVO;
import com.jiang.meskill.error.BusinessException;
import com.jiang.meskill.response.CommonReturnType;
import com.jiang.meskill.service.CacheService;
import com.jiang.meskill.service.ItemService;
import com.jiang.meskill.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author jiangs
 * @create 2022-04-14-10:58
 */
@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/create")
    public CommonReturnType createItem(@RequestParam(name = "title") String title,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "stock") Integer stock,
                                       @RequestParam(name = "imgUrl") String imgUrl) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        itemModel.setPrice(price);
        itemModel.setTitle(title);
        itemModel.setStock(stock);
        ItemModel item = itemService.createItem(itemModel);
        ItemVO itemVO = convertFromItemModel(item);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping("/get")
    public CommonReturnType getItem(@RequestParam(name = "id")Integer id){
        ItemModel itemModel = null;
        //先取本地缓存
        itemModel = (ItemModel) cacheService.getCommonCache("item_"+id);
        if(itemModel==null){
            itemModel = (ItemModel)redisTemplate.opsForValue().get("item_" + id);
            if(itemModel==null){
                itemModel = itemService.getByItemId(id);
                redisTemplate.opsForValue().set("item_"+id, itemModel);
                redisTemplate.expire("item_"+id, 10, TimeUnit.MINUTES);
            }
            cacheService.setCommonCache("item_"+id, itemModel);
        }
        ItemVO itemVO = convertFromItemModel(itemModel);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping("/list")
    public CommonReturnType listItem(){
        List<ItemModel> itemModelList = itemService.listItem();
        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = convertFromItemModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(itemVOList);
    }

    public ItemVO convertFromItemModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);
        if(itemModel.getPromoModel()!=null){
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().
                    toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else{
            itemVO.setPromoStatus(0);
        }
        return itemVO;
    }

}
