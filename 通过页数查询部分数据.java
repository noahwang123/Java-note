/**
 * 通过当前页数查询数据库
 * PageHelper 能直接查询到所有的数据库数据再实现分页
 * 当数据量太大时会导致查询非常缓慢
 * 如何实现
 */

 /**
  * @param 某VO = CallRecordVO
  * @param pageSize 每页显示数量
  * @param pageNum 当前页数
  */

  /**
   * Controller 端 
   */
  @Resource
  private CallRecordOpeService callRecordOpeService;

  @GetMapping("/queryCallRecord")
  public ResultModel queryCallRecord(@RequestParam(value = "pageSize", defaultValue = "20",required = false)Integer pageSize,
                                     @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum) {
      SimplePageInfo<CallRecordVO> simplePageInfo = callRecordOpeService.queryCallRecord(pageSize, pageNum);
      return ResultModel.success(simplePageInfo, "成功获取");
  }

  /**
   * Service 端
   */
  public interface CallRecordOpeService extends EngineService<CallRecordPO> {
    SimplePageInfo<CallRecordVO> queryCallRecord(Integer pageSize, Integer pageNum);
    }

  /**
   * ServiceImpl 端 
   */
  @Resource
  private CallRecordPOMapper callRecordPOMapper;

  @Override
  public SimplePageInfo<CallRecordVO> queryCallRecord(Integer pageSize, Integer pageNum) {
      List<CallRecordVO> callRecordList;
      Integer start = (pageNum-1)*pageSize+1; //通过不同的分页信息算出起始数据和最终数据，并传入sql查询
      Integer end = pageNum*pageSize;
      callRecordList = callRecordPOMapper.queryCallRecord(start, end);
      return SimplePageInfo.of(callRecordList);
  }

  /**
   * POMapper端
   */
  List<CallRecordVO> queryCallRecord(
    @RequestParam Integer start,
    @RequestParam Integer end);
  }
  /**
   * POMapper sql端
   */
  <select id="queryCallRecord" resultMap="callRecordOPE">
  select
  <include refid="callRecordOPE"/>
  from call_record
  LIMIT #{param1}, #{param2};
  <!-- param1=start, param2=end -->
    </select>