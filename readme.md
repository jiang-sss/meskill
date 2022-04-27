# meskill总结

## 1. VO,BO,PO,DO,DTO的理解

**PO**（Persistant Object）持久对象，和Entity和DO是一样的，仅仅是数据库的表映射的Java对象，两者保持一致。对应meskill项目中，数据库中存在user_info表，则对应后端中会存在一个UserDo的映射对象，两者属性保持一致，没有任何数据操作，只有拥有 getter/setter 方法，PO存在Dao层。

**DAO** 是 Data Access Object 的缩写，用于表示一个数据访问对象。使用 DAO 访问数据库，包括插入、更新、删除、查询等操作，与 PO 一起使用。DAO 一般在持久层，完全封装数据库操作，对外暴露的方法使得上层应用不需要关注数据库相关的任何信息，对应的是一个个的mapper，如UserDOMapper。

**BO**（bussines object）业务层对象，BO是对PO的组合，主要在服务内部使用的业务对象，对应meskill项目中的Model，如UserModel组合了UserDo和UserPasswordDo。

**DTO**（Data Transfer Object）数据传输对象。DTO有两种存在形式：

- 在后端，他的存在形式是请求的入参，也就是在controller里面定义的参数
- 在前端，他的存在形式通常是js里面的对象（也可以简单理解成json），也就是通过ajax请求的那个数据体

**VO**（Value Object）值对象，VO就是展示用的数据，不管展示方式是网页，还是客户端，还是APP，只要是这个东西是让人看到的，这就叫VO VO主要的存在形式就是js里面的对象（也可以简单理解成json）

这里DTO和VO区别有点模糊，网上的一种说法，比如后端controller层传的是DTO, 里面的gender的值为1，到前端的VO层对应显示的是男（其实这个逻辑也可以在后端就实现了）。按照这个理解meskill项目中的itemVo其实是dto。

## 2. 登录注册模块

### 2.1 验证码注册模块

#### 2.1.1 验证码绑定

1. 按照一定规则生成验证码
2. 将验证码与用户手机进行关联
   - 通过session实现
   - 通过redis实现
3. 验证码通过手机短信通道发送给用户

#### 2.1.2 数据验证

这里定义ValidatorImpl类集成Validator，保存校验信息，目的是为了异常统一处理，validator校验通常返回的是BindException和MethodArgumentNotValidException错误。

```java
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object bean){
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> validateSet = validator.validate(bean);
        if(validateSet.size()>0){
            validationResult.setHasError(true);
            validateSet.forEach(validate->{
                String message = validate.getMessage();
                String propertyName = validate.getPropertyPath().toString();
                validationResult.getErrorMsgMap().put(propertyName, message);
            });
        }
        return validationResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //validator采用工厂模式
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
```

### 2.2 登录模块

1. 进行账号密码校验
2. 登录成功后将user信息保存在session中，后续其他模块会使用该信息。

### 3. 商品模块

设计表结构时应优先考虑业务模型的设计，优先设计ItemModel。

商品的业务模型，首先几个基本的属性如商品id, title, price, description, stock, sales, imgUrl。

后续设计秒杀模块，会再次对商品的业务模型进行调整。

而在表设计上，item表会对上面属性进行拆分，另外拆出一个库存表itemStock。

```java
package com.jiang.meskill.service.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author jiangs
 * @create 2022-04-13-20:47
 */
@Data
public class ItemModel {
    private Integer id;

    //如果promoModel不为空，则表示其拥有还未结束的秒杀活动
    private PromoModel promoModel;

    @NotBlank(message = "商品名称不能为空")
    private String title;
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "商品价格必须大于等于0")
    private BigDecimal price;
    @NotNull(message = "库存不能不填")
    private Integer stock;
    @NotBlank(message = "商品描述不能为空")
    private String description;
    private Integer sales;
    @NotBlank(message = "图片信息不能为空")
    private String imgUrl;
}

```

在业务上，主要是商品的查询，新建，减库存与加销量操作。

#### 3.1 生成

### 4. 订单模块

目前仅设计一个订单下单一种商品，数量不限。设计订单的业务层模型，包括订单单号id, 订单对应的用户userId，对应的商品itemId，购买数量amount以及订单总金额orderPrice。

另外两个字段用于秒杀模块，分别是秒杀编号promoId以及秒杀价格itemPrice。

```java
@Data
//用户下单的交易模型
public class OrderModel {
    //交易单号
    private String id;

    //订单用户
    private Integer userId;

    //订单商品
    private Integer itemId;

    //若非空则以秒杀商品下单
    private Integer promoId;

    //冗余字段,若promoId非空，则表示秒杀商品价格
    private BigDecimal itemPrice;

    //购买数量
    private Integer amount;

    //购买金额
    private BigDecimal orderPrice;
}
```

#### 4.1 下单

1. 从session中获取当前登录用户的信息
2. 数据校验

```java
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
```

3. 减库存（事务型操作）
4. 订单入库(插入表、商品销量增加)

其中订单入库需要生成订单编号，这里订单编号利用数据库生成。

```java
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
```

### 5. 秒杀模块

秒杀业务的业务模型，这里每个秒杀活动仅适应一个商品。

```java
@Data
public class PromoModel {
    private Integer id;

    //为1表示还未开始，为2表示进行中，3表示已结束
    private Integer status;

    //秒杀活动名称
    private String promoName;

    //秒杀活动的开始时间
    private DateTime startDate;

    //秒杀活动的结束时间
    private DateTime endDate;

    //秒杀活动的适用商品
    private Integer itemId;

    //秒杀活动的商品价格
    private BigDecimal promoItemPrice;

}
```

设计秒杀模块后，对于商品模块和订单模块就需要进行调整，

#### 5.1 商品模块

1. 首先一个商品业务模型需要聚合一个PromoModel，如果promoModel不为空，则表示其拥有还未结束的秒杀活动。
2. 商品业务的查询，需要同时查询商品对应的活动，当活动未结束是需要设置商品对应的活动

```java
public ItemModel getByItemId(Integer id) {
    ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
    if(itemDO==null){
        return null;
    }
    ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
    ItemModel itemModel = convertModelFromDO(itemDO, itemStockDO);

    PromoModel promoModel = promoService.getPromoByItemId(itemModel.getId());
    if(promoModel!=null&&promoModel.getStatus()!=3){
        itemModel.setPromoModel(promoModel);
    }
    return itemModel;
}
```

3. 订单的创建，需要将商品的购买价格设置为活动价格。

```java
if(promoId!=null){
    orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
}
orderModel.setOrderPrice(orderModel.getItemPrice().multiply(BigDecimal.valueOf(amount)));
```

