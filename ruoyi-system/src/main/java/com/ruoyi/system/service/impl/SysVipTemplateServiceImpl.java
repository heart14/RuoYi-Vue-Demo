package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysVipTemplate;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.mapper.SysVipTemplateMapper;
import com.ruoyi.system.service.ISysVipTemplateService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wfli
 * @since 2024/2/7 14:04
 */
@Service
public class SysVipTemplateServiceImpl implements ISysVipTemplateService {

    private final SysVipTemplateMapper sysVipTemplateMapper;

    private final RedisCache redisCache;

    private final RedisTemplate redisTemplate;

    public SysVipTemplateServiceImpl(SysVipTemplateMapper sysVipTemplateMapper, RedisCache redisCache, RedisTemplate redisTemplate) {
        this.sysVipTemplateMapper = sysVipTemplateMapper;
        this.redisCache = redisCache;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 查询会员套餐模板
     *
     * @param templateId 会员套餐模板主键
     * @return 会员套餐模板
     */
    @Override
    public SysVipTemplate selectSysVipTemplateByTemplateId(String templateId) {
        return sysVipTemplateMapper.selectSysVipTemplateByTemplateId(templateId);
    }

    /**
     * 查询会员套餐模板列表
     *
     * @param sysVipTemplate 会员套餐模板
     * @return 会员套餐模板
     */
    @Override
    public List<SysVipTemplate> selectSysVipTemplateList(SysVipTemplate sysVipTemplate) {
        return sysVipTemplateMapper.selectSysVipTemplateList(sysVipTemplate);
    }

    /**
     * 新增会员套餐模板
     *
     * @param sysVipTemplate 会员套餐模板
     * @return 结果
     */
    @Override
    public int insertSysVipTemplate(SysVipTemplate sysVipTemplate) {
        long template_seq = redisTemplate.opsForValue().increment(CacheConstants.SYS_CONFIG_KEY+"template_seq", 1L);
        sysVipTemplate.setTemplateId("TC" + DateUtils.dateTimeNow(DateUtils.YYYY_MM.replace("-", "")) + String.format("%03d",template_seq));
        if (sysVipTemplate.getStatus() == null) {
            sysVipTemplate.setStatus(Constants.STATUS_ZERO);// 0-未启用，1-已使用
        }
        sysVipTemplate.setCreateTime(DateUtils.getNowDate());
        return sysVipTemplateMapper.insertSysVipTemplate(sysVipTemplate);
    }

    /**
     * 修改会员套餐模板
     *
     * @param sysVipTemplate 会员套餐模板
     * @return 结果
     */
    @Override
    public int updateSysVipTemplate(SysVipTemplate sysVipTemplate) {
        sysVipTemplate.setUpdateTime(DateUtils.getNowDate());
        return sysVipTemplateMapper.updateSysVipTemplate(sysVipTemplate);
    }

    /**
     * 批量删除会员套餐模板
     *
     * @param templateIds 需要删除的会员套餐模板主键
     * @return 结果
     */
    @Override
    public int deleteSysVipTemplateByTemplateIds(String[] templateIds) {
        return sysVipTemplateMapper.deleteSysVipTemplateByTemplateIds(templateIds);
    }

    /**
     * 删除会员套餐模板信息
     *
     * @param templateId 会员套餐模板主键
     * @return 结果
     */
    @Override
    public int deleteSysVipTemplateByTemplateId(String templateId) {
        return sysVipTemplateMapper.deleteSysVipTemplateByTemplateId(templateId);
    }
}
