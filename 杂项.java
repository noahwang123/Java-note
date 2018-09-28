//添加creationTime和lastModifedTime, 只需要再PO/VO里加好字段(不需要getter&setter)
//字段前需要    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//POMapper的resultMap加<result column="creation_time" jdbcType="TIMESTAMP" property="creationTime"/>
//<sql>里加creation_time就行，sql里设置成自动获取creationtime

/**
 * 当ResultMap被别的PO占用时，可以新建一个resultMap extends 原来的。然后再加。
 * <resultMap id="CallRecordWithJobInfoBO" type="com.yiwise.core.model.bo.callrecord.CallRecordWithJobInfoBO" extends="BaseResultMap">
 *  <result column="creation_time" jdbcType="TIMESTAMP" property="creationTime"/>
 * </resultMap>
 */ 

 //创建工具类时，为防止别人new, 可抛出一个异常
 public class DemoUtils{
     private DemoUtils() {
         //这样就不能new了，最好加个注释
         throw new AssertionError();
     }
 }