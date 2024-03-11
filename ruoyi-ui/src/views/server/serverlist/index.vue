<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px" size="small">
      <el-form-item label="服务器名" prop="nameTag">
        <el-input
          v-model="queryParams.nameTag"
          clearable
          placeholder="请输入服务器名称标签"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务器IP" prop="ip">
        <el-input
          v-model="queryParams.ip"
          clearable
          placeholder="请输入服务器IP"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="远程端口" prop="rconPort">
        <el-input
          v-model="queryParams.rconPort"
          clearable
          placeholder="请输入远程端口号"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime"
                        clearable
                        placeholder="请选择创建时间"
                        type="date"
                        value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建者" prop="creatBy">
        <el-input
          v-model="queryParams.creatBy"
          clearable
          placeholder="请输入创建者"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['server:serverlist:add']"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['server:serverlist:edit']"
          :disabled="single"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['server:serverlist:remove']"
          :disabled="multiple"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['server:serverlist:export']"
          icon="el-icon-download"
          plain
          size="mini"
          type="warning"
          @click="handleExport"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['server:serverlist:refresh']"
          icon="el-icon-refresh"
          plain
          size="mini"
          type="info"
          @click="handleRefresh"
        >刷新缓存
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="serverlistList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="主键ID" prop="id"/>
      <el-table-column align="center" label="服务器名称标签" prop="nameTag"/>
      <el-table-column align="center" label="服务器IP" prop="ip"/>
      <el-table-column align="center" label="远程端口号" prop="rconPort"/>
      <el-table-column align="center" label="创建时间" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建者" prop="createBy"/>
      <el-table-column align="center" label="启用状态" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.server_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="描述" prop="remark"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['server:serverlist:edit']"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            v-hasPermi="['server:serverlist:remove']"
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改服务器信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-position="left" label-width="100px"
               size="medium">
        <el-form-item label="服务器名称" prop="nameTag">
          <el-input v-model="form.nameTag" :style="{width: '100%'}" clearable placeholder="请输入服务器名称">
          </el-input>
        </el-form-item>
        <el-form-item label="IP/域名" prop="ip">
          <el-input v-model="form.ip" :style="{width: '100%'}" clearable placeholder="请输入服务器IP/域名">
          </el-input>
        </el-form-item>
        <el-form-item label="远程端口号" prop="rconPort">
          <el-input v-model="form.rconPort" :style="{width: '100%'}" clearable placeholder="请输入远程端口号">
          </el-input>
        </el-form-item>
        <el-form-item label="远程密码" prop="rconPassword">
          <el-input v-model="form.rconPassword" :style="{width: '100%'}" clearable placeholder="请输入远程密码">
          </el-input>
        </el-form-item>
        <el-form-item label="描述" prop="remark">
          <el-input v-model="form.remark" :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"
                    placeholder="请输入描述" type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="启用" prop="status" required>
          <el-switch v-model="form.status" active-color="#67F48A" inactive-color="#EF4E4E"></el-switch>
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
import {
  addServerlist,
  delServerlist,
  getServerlist,
  listServerlist,
  refreshCache,
  updateServerlist
} from "@/api/server/serverlist";

export default {
  inheritAttrs: false,
  components: {},
  dicts: ['server_status'],
  props: [],
  name: "Serverlist",
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
      showSearch: true,
      // 总条数
      total: 0,
      // 服务器信息表格数据
      serverlistList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uuid: null,
        nameTag: null,
        ip: null,
        rconPort: null,
        rconPassword: null,
        createTime: null,
        createBy: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        nameTag: [{
          required: true,
          message: '请输入服务器名称',
          trigger: 'blur'
        }],
        ip: [{
          required: true,
          message: '请输入服务器IP',
          trigger: 'blur'
        }],
        rconPort: [{
          required: true,
          message: '请输入远程端口号',
          trigger: 'blur'
        }],
        rconPassword: [{
          required: true,
          message: '请输入远程密码',
          trigger: 'blur'
        }]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询服务器信息列表 */
    getList() {
      this.loading = true;
      listServerlist(this.queryParams).then(response => {
        this.serverlistList = response.rows;
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
        nameTag: null,
        ip: null,
        rconPort: null,
        rconPassword: null,
        status: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加服务器信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getServerlist(id).then(response => {
        this.form = response.data;
        this.form.status = this.form.status === 1;
        this.open = true;
        this.title = "修改服务器信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.status = this.form.status ? 1 : 0;
          if (this.form.id != null) {
            updateServerlist(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addServerlist(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除服务器信息编号为"' + ids + '"的数据项？').then(function () {
        return delServerlist(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('server/serverlist/export', {
        ...this.queryParams
      }, `serverlist_${new Date().getTime()}.xlsx`)
    },
    handleRefresh() {
      refreshCache().then(() => {
        this.$modal.msgSuccess("刷新成功");
      })
    }
  }
};
</script>
