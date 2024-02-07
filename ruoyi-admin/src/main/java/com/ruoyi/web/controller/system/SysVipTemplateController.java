package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysVipTemplate;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysVipTemplateService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author wfli
 * @since 2024/2/7 14:06
 */
@RestController
@RequestMapping("/system/template")
public class SysVipTemplateController extends BaseController {
    private final ISysVipTemplateService sysVipTemplateService;

    public SysVipTemplateController(ISysVipTemplateService sysVipTemplateService) {
        this.sysVipTemplateService = sysVipTemplateService;
    }

    /**
     * 查询会员套餐模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysVipTemplate sysVipTemplate) {
        startPage();
        List<SysVipTemplate> list = sysVipTemplateService.selectSysVipTemplateList(sysVipTemplate);
        for (SysVipTemplate vipTemplate : list) {
            if (vipTemplate.getOriginPrice() != null) {
                BigDecimal bigDecimal = new BigDecimal(vipTemplate.getOriginPrice());
                BigDecimal divide = bigDecimal.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);//两位小数，四舍五入
                vipTemplate.setOriginPrice(divide.toString());
            }
            if (vipTemplate.getRealPrice() != null) {
                BigDecimal bigDecimal = new BigDecimal(vipTemplate.getRealPrice());
                BigDecimal divide = bigDecimal.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);//两位小数，四舍五入
                vipTemplate.setRealPrice(divide.toString());
            }

        }
        return getDataTable(list);
    }

    /**
     * 导出会员套餐模板列表
     */
    @PreAuthorize("@ss.hasPermi('system:template:export')")
    @Log(title = "导出会员套餐模板列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysVipTemplate sysVipTemplate) {
        List<SysVipTemplate> list = sysVipTemplateService.selectSysVipTemplateList(sysVipTemplate);
        for (SysVipTemplate vipTemplate : list) {
            if (vipTemplate.getOriginPrice() != null) {
                BigDecimal bigDecimal = new BigDecimal(vipTemplate.getOriginPrice());
                BigDecimal divide = bigDecimal.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);//两位小数，四舍五入
                vipTemplate.setOriginPrice(divide.toString());
            }
            if (vipTemplate.getRealPrice() != null) {
                BigDecimal bigDecimal = new BigDecimal(vipTemplate.getRealPrice());
                BigDecimal divide = bigDecimal.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);//两位小数，四舍五入
                vipTemplate.setRealPrice(divide.toString());
            }
        }
        ExcelUtil<SysVipTemplate> util = new ExcelUtil<SysVipTemplate>(SysVipTemplate.class);
        util.exportExcel(response, list, "会员套餐模板数据");
    }

    /**
     * 获取会员套餐模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:template:query')")
    @GetMapping(value = "/{templateId}")
    public AjaxResult getInfo(@PathVariable("templateId") String templateId) {
        SysVipTemplate vipTemplate = sysVipTemplateService.selectSysVipTemplateByTemplateId(templateId);
        if (vipTemplate.getOriginPrice() != null) {
            BigDecimal bigDecimal = new BigDecimal(vipTemplate.getOriginPrice());
            BigDecimal divide = bigDecimal.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);//两位小数，四舍五入
            vipTemplate.setOriginPrice(divide.toString());
        }
        if (vipTemplate.getRealPrice() != null) {
            BigDecimal bigDecimal = new BigDecimal(vipTemplate.getRealPrice());
            BigDecimal divide = bigDecimal.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);//两位小数，四舍五入
            vipTemplate.setRealPrice(divide.toString());
        }
        return success(vipTemplate);
    }

    /**
     * 新增会员套餐模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:add')")
    @Log(title = "新增会员套餐模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysVipTemplate sysVipTemplate) {
        if (sysVipTemplate.getOriginPrice() != null) {
            //将单位为元的金额转成单位分
            BigDecimal bigDecimal = new BigDecimal(sysVipTemplate.getOriginPrice());
            BigDecimal multiply = bigDecimal.multiply(new BigDecimal("100"));
            sysVipTemplate.setOriginPrice(multiply.toString());
        }
        if (sysVipTemplate.getRealPrice() != null) {
            //将单位为元的金额转成单位分
            BigDecimal bigDecimal = new BigDecimal(sysVipTemplate.getRealPrice());
            BigDecimal multiply = bigDecimal.multiply(new BigDecimal("100"));
            sysVipTemplate.setRealPrice(multiply.toString());
        }
        return toAjax(sysVipTemplateService.insertSysVipTemplate(sysVipTemplate));
    }

    /**
     * 修改会员套餐模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:edit')")
    @Log(title = "修改会员套餐模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysVipTemplate sysVipTemplate) {
        if (sysVipTemplate.getOriginPrice() != null) {
            //将单位为元的金额转成单位分
            BigDecimal bigDecimal = new BigDecimal(sysVipTemplate.getOriginPrice());
            BigDecimal multiply = bigDecimal.multiply(new BigDecimal("100"));
            sysVipTemplate.setOriginPrice(multiply.toString());
        }
        if (sysVipTemplate.getRealPrice() != null) {
            //将单位为元的金额转成单位分
            BigDecimal bigDecimal = new BigDecimal(sysVipTemplate.getRealPrice());
            BigDecimal multiply = bigDecimal.multiply(new BigDecimal("100"));
            sysVipTemplate.setRealPrice(multiply.toString());
        }
        return toAjax(sysVipTemplateService.updateSysVipTemplate(sysVipTemplate));
    }

    /**
     * 删除会员套餐模板
     */
    @PreAuthorize("@ss.hasPermi('system:template:remove')")
    @Log(title = "删除会员套餐模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{templateIds}")
    public AjaxResult remove(@PathVariable String[] templateIds) {
        return toAjax(sysVipTemplateService.deleteSysVipTemplateByTemplateIds(templateIds));
    }
}
