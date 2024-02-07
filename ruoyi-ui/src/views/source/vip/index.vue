<template>
  <div class="app-container">
    <!-- 顶部搜索栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="套餐名称" prop="templateName">
        <el-input
          v-model="queryParams.templateName"
          placeholder="请输入套餐名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="hot标题" prop="hotTitle">
        <el-input
          v-model="queryParams.hotTitle"
          placeholder="请输入hot标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原价" prop="originPrice">
        <el-input
          v-model="queryParams.originPrice"
          placeholder="请输入原价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="划线价" prop="realPrice">
        <el-input
          v-model="queryParams.realPrice"
          placeholder="请输入划线价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="VIP时长" prop="duration">
        <el-input
          v-model="queryParams.duration"
          placeholder="请输入VIP时长"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="VIP时长单位" prop="durationUnit">
        <el-input
          v-model="queryParams.durationUnit"
          placeholder="年 | 月 | 天"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="展示顺序" prop="showIndex">
        <el-input
          v-model="queryParams.showIndex"
          placeholder="请输入展示顺序"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 增删改按钮栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:template:add']"
        >新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:template:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:template:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:template:export']"
        >导出</el-button> 
      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 数据展示table -->
    <el-table v-loading="loading" :data="templateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="套餐ID" align="center" prop="templateId" />
      <el-table-column label="套餐名称" align="center" prop="templateName" />
      <el-table-column label="hot标题" align="center" prop="hotTitle" />
      <el-table-column label="原价" align="center" prop="originPrice" />
      <el-table-column label="划线价" align="center" prop="realPrice" />
      <el-table-column label="VIP时长" align="center" prop="duration,durationUnit" >
        <template v-slot="scope">
            {{ scope.row.duration }}{{ scope.row.durationUnit }}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" >
        <template v-slot="scope">
          <span v-if="scope.row.status==0" style="color:red">未启用</span>
          <span v-if="scope.row.status==1" style="color:green">使用中</span>
        </template>
      </el-table-column>
      <el-table-column label="展示顺序" align="center" prop="showIndex" />
      <!-- <el-table-column label="备注" align="center" prop="remark" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:template:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:template:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改会员套餐模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="套餐名称" prop="templateName" required>
          <el-input v-model="form.templateName" placeholder="请输入套餐名称" />
        </el-form-item>
        <el-form-item label="hot标题" prop="hotTitle">
          <el-input v-model="form.hotTitle" placeholder="请输入hot标题" />
        </el-form-item>
        <el-form-item label="原价" prop="originPrice">
          <el-input v-model="form.originPrice" placeholder="请输入原价" />
        </el-form-item>
        <el-form-item label="划线价" prop="realPrice">
          <el-input v-model="form.realPrice" placeholder="请输入划线价" />
        </el-form-item>
        <el-form-item label="VIP时长" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入VIP时长" />
        </el-form-item>
        <el-form-item label="VIP时长的单位" prop="durationUnit">
          <el-input v-model="form.durationUnit" placeholder="年 | 月 | 天" />
        </el-form-item>
        <el-form-item label="展示顺序" prop="showIndex">
          <el-input v-model="form.showIndex" placeholder="请输入展示顺序" />
        </el-form-item>
        <el-form-item label="是否启用" prop="status">
          <el-radio-group v-model="form.status" size="medium">
            <el-radio v-for="(item, index) in statusOptions" :key="index" :label="item.value"
              :disabled="item.disabled">{{item.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTemplate, getTemplate, delTemplate, addTemplate, updateTemplate } from "@/api/system/template";

export default {
  name: "Template",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: false,
      // 总条数
      total: 0,
      // 会员套餐模板表格数据
      templateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        templateName: null,
        hotTitle: null,
        originPrice: null,
        realPrice: null,
        duration: null,
        durationUnit: null,
        status: null,
        showIndex: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        templateName:[{
          required: true,
          message: '请输入套餐名称',
          trigger: 'change'
        }],
        originPrice:[{
          required: true,
          message: '请输入套餐原价',
          trigger: 'change'
        }],
        duration:[{
          required: true,
          message: '请输入套餐时长',
          trigger: 'change'
        }],
        status:[{
          required: true,
          message: '请选择套餐使用状态',
          trigger: 'change'
        }]
      },
      statusOptions: [{
        "label": "未启用",
        "value": 0
      }, {
        "label": "使用中",
        "value": 1
      }]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询会员套餐模板列表 */
    getList() {
      this.loading = true;
      listTemplate(this.queryParams).then(response => {
        this.templateList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        templateId: null,
        templateName: null,
        hotTitle: null,
        originPrice: null,
        realPrice: null,
        duration: null,
        durationUnit: null,
        status: null,
        showIndex: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.templateId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加会员套餐模板";
    },
    /** 修改按钮操作 */
    // todo 新增或更新时，展示顺序字段？
    handleUpdate(row) {
      this.reset();
      const templateId = row.templateId || this.ids
      getTemplate(templateId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员套餐模板";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.templateId != null) {
            updateTemplate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTemplate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const templateIds = row.templateId || this.ids;
      this.$modal.confirm('是否确认删除会员套餐模板编号为"' + templateIds + '"的数据项？').then(function() {
        return delTemplate(templateIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/template/export', {
        ...this.queryParams
      }, `template_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
