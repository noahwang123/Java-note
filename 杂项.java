//创建工具类时，为防止别人new, 可抛出一个异常
 public class DemoUtils{
     private DemoUtils() {
         //这样就不能new了，最好加个注释
         throw new AssertionError();
     }
 }

 //如果遇到异常抛出异常并继续
 try {
    userService.addUser(user, SystemEnum.BOSS);
} catch (Exception e) {
    if (e instanceof DuplicateKeyException && e.getMessage().contains("Duplicate entry")) {
        throw new CommonException(CommonErrorCode.RESOURCE_CONFLICT, "联系方式重复,请确认!");
    }
    logger.error(e.getMessage());
}

//@Data 可以代替所有的getter&setter
@Data
//全参构造函数
@AllArgsConstructor
//无参构造函数
@NoArgsConstructor
