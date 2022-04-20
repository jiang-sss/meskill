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

