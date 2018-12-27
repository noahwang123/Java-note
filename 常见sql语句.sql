    
    <update id="linkAsrModel">
        update dialog_flow_info
        set dialog_flow_asr_model_id = #{asrModelId}
        where
          id in
            <foreach collection="dialogFlowIds" index="index" item="dialogFlowId" separator="," open="(" close=")">
                #{dialogFlowId}
            </foreach>
    </update>

    -- 查询一个list, 为防止在size=0时查出来所有数据，最好加上1=0
            <if test="distributorIdList != null and distributorIdList.size > 0">
                and t.distributor_id in
                <foreach collection="distributorIdList" item="id" separator="," open="(" close=")">
                    #{id}
                </foreach>
            </if>
            <if test="distributorIdList.size()==0">
                and 1=0
            </if>
            
    <sql id="bossAndOpe">
      <choose>
          <when test="request.tenantId != null and request.tenantId.size > 0">
              and cr.tenant_id in
              <foreach collection="request.tenantId" item = "id" separator="," open="(" close=")">
                  #{id}
              </foreach>
          </when>
          <when test="request.tenant.size == 0">
              and 1=0
          </when>
          <otherwise>
              <include refid="condition"/>
          </otherwise>
      </choose>
    </sql>

update organization o, user u 
set o.manager_user_id = 0 where o.manager_user_id = u.id and u.organization_id != o.id;