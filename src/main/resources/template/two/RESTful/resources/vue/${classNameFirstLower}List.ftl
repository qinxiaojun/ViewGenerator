<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

<template>
    <div class='index-analysis'>
        <h2>查询表格</h2>
        <div class="analysis-table">
            <my-form
                    :ref='formConfig.ref'
                    :formConfig="formConfig"
            ></my-form>
            <my-table :data="tableData" :columns="columns" :other='other' :ref='other.ref'></my-table>
            <div class="page-list">
                <el-pagination
                        background
                        layout="total, prev, pager, next"
                        :current-page.sync='pageObj.currentPage'
                        :page-size='pageObj.pageSize'
                        :total="pageObj.total"
                        @current-change="pageChange"
                >
                </el-pagination>
            </div>
        </div>
        <el-dialog :title="dialogBox.isEdit?'编辑数据':'新增数据'" :visible.sync="dialogBox.boxShow" width='640px'>
            <my-form
                    :ref='formConfigs.ref'
                    :formConfig="formConfigs"
            ></my-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogBox.boxShow = false">取 消</el-button>
                <el-button type="primary" @click="confirSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<style scoped lang="scss">
    .index-analysis {

    h2 {
        font-size: 20px;
        margin-bottom: 40px;
    @include font-color($ color-G90, $ color-W90);
    }

    .analysis-table {
    @include bg-color($ color-W100, $ color-C70);
        padding: 32px 24px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        border-radius: 4px;

    .table-btn {
        display: inline-block;
        padding: 0 20px;
        border-radius: 4px;
        margin-left: 10px;
        border: 1px solid;
        cursor: pointer;
    @include font-color($ color-G80, $ color-W90);
    @include bg-color($ color-W100, $ color-C50);
    @include bd-color($ color-G40, $ color-W10);
    }

    }
    }
</style>

<script>
    import MyTable from '@/components/MyTable'
    import MyForm from '@/components/MyForm'
    import Format from "@/utils/format.js"

    export default {
        data() {
            return {
                other: {
                    ref: 'table'
                },
                dialogBox: {
                    boxShow: false,
                    isEdit: false,
                    idItem: ''
                },
                pageObj: {
                    pageSize: 8,
                    total: 0,
                    currentPage: 1,
                },
                tableData: [],
                //搜索
                formConfig: {
                    labelWidth: '80px',
                    ref: 'formModel',
                    inline: true,
                    labelPosition: 'right',
                    marginRight: '10px',
                    formItemList: [
                        <#if searchFields ?eval?size gt 0>
                        <#list searchFields ?eval as searchField>
                        <#if searchField.columnSearchType == 'text'>
                        {
                            type: "text",
                            prop: "${searchField.columnName}",
                            width: '180px',
                            label: "${searchField.columnAlias}",
                            placeholder: "${searchField.columnAlias}"
                        }<#if !searchField?is_last>, </#if>
                        <#elseif searchField.columnSearchType == 'singleDate'>
                        {
                            type: "date",
                            prop: "${searchField.columnName}",
                            width: '180px',
                            label: "${searchField.columnAlias}",
                            placeholder: "请输入${searchField.columnAlias}"
                        }<#if !searchField?is_last>, </#if>
                        <#elseif searchField.columnSearchType == 'select'>
                        {
                            type: "select",
                            prop: "${searchField.columnName}",
                            width: '180px',
                            label: "${searchField.columnAlias}选择",
                            placeholder: "请选择${searchField.columnAlias}",
                            arrList: []
                        }<#if !searchField?is_last>, </#if>
                        <#elseif searchField.columnSearchType == 'dateDuring'>
                        {
                            type: "date",
                            dateType: 'daterange',
                            prop: "${searchField.columnName}",
                            width: '280px',
                            label: "${searchField.columnAlias}查询",
                            placeholder: "请输入${searchField.columnAlias}"
                        }<#if !searchField?is_last>, </#if>
                        </#if>
                        </#list>
                        </#if>
                    ],
                    operate: [
                        {
                            name: '查询',
                            handleClick: this.getDataList
                        },
                        {
                            name: '添加',
                            handleClick: this.addData
                        },
                        {
                            name: '重置',
                            type: "transparent",
                            handleClick: this.resetForm
                        }
                    ],
                    formModel: {
            <#if searchFields ?eval?size gt 0>
            <#list searchFields ?eval as searchField>
            <#if searchField.columnSearchType == 'dateDuring'>
            ${searchField.columnName}:
            []<#if !searchField?is_last>,
            </#if>
            <#else>
            ${searchField.columnName}:
            ''<#if !searchField?is_last>,
            </#if>
            </#if>
            </#list>
            </#if>
        }
        },
            //表单
            formConfigs: {
                labelWidth: '80px',
                    ref
            :
                'formModels',
                    labelPosition
            :
                'right',
                    marginRight
            :
                '10px',
                    formItemList
            :
                [
                    <#if formFields ?eval?size gt 0>
                    <#list formFields ?eval as formField>
                    <#if formField.componentType == 'text'>
                    {
                        type: "text",
                        prop: "${formField.columnName}",
                        width: '450px',
                        label: "${formField.columnAlias}",
                        placeholder: "${formField.columnAlias}"
                    }<#if !formField?is_last>, </#if>
                    <#elseif formField.componentType == 'singleDate'>
                    {
                        type: "date",
                        prop: "${formField.columnName}",
                        width: '450px',
                        label: "${formField.columnAlias}",
                        placeholder: "请输入${formField.columnAlias}"
                    }<#if !formField?is_last>, </#if>
                    <#elseif formField.componentType == 'select'>
                    {
                        type: "select",
                        prop: "${formField.columnName}",
                        width: '450px',
                        label: "${formField.columnAlias}",
                        placeholder: "请选择${formField.columnAlias}",
                        arrList: []
                    }<#if !formField?is_last>, </#if>
                    <#elseif formField.componentType == 'dateDuring'>
                    {
                        type: "date",
                        dateType: 'daterange',
                        prop: "${formField.columnName}",
                        width: '450px',
                        label: "${formField.columnAlias}",
                        placeholder: "请选择${formField.columnAlias}范围"
                    }<#if !formField?is_last>, </#if>
                    <#elseif formField.componentType == 'radio'>
                    {
                        type: "radio",
                        prop: "${formField.columnName}",
                        label: "${formField.columnAlias}",
                        arrList: []
                    }<#if !formField?is_last>, </#if>
                    <#elseif formField.componentType == 'checkbox'>
                    {
                        type: "checkbox",
                        prop: "${formField.columnName}",
                        label: "${formField.columnAlias}",
                        arrList: []
                    }<#if !formField?is_last>, </#if>
                    <#elseif formField.componentType == 'slider'>
                    {
                        type: "slider",
                        prop: "${formField.columnName}",
                        width: '280px',
                        label: "${formField.columnAlias}"
                    }<#if !formField?is_last>, </#if>
                    <#elseif formField.componentType == 'textarea'>
                    {
                        type: "textarea",
                        prop: "${formField.columnName}",
                        width: '280px',
                        label: "${formField.columnAlias}",
                        placeholder: "请输入${formField.columnAlias}"
                    }<#if !formField?is_last>, </#if>
                    </#if>
                    </#list>
                    </#if>
                ],
                    formModel
            :
                {
                    <#if formFields ?eval?size gt 0>
                    <#list formFields ?eval as formField>
                    <#if formField.componentType == 'dateDuring'>
                    ${formField.columnName}:
                    []<#if !formField?is_last>,
                    </#if>
                    <#else>
                    ${formField.columnName}:
                    ''<#if !formField?is_last>,
                    </#if>
                    </#if>
                    </#list>
                    </#if>
                }
            ,
                rules: {
                    <#if formFields ?eval?size gt 0>
                    <#list formFields ?eval as formField>
                    <#if formField.componentType == 'text'>
                    ${formField.columnName}:
                    [{
                        required: true,
                        validator: Format.FormValidate.Form('${formField.columnAlias}').NoEmpty,
                        trigger: 'blur'
                    }]<#if !formField?is_last>,
                    </#if>
                    <#else>
                    ${formField.columnName}:
                    [{
                        required: true,
                        validator: Format.FormValidate.Form('${formField.columnAlias}').TypeSelect,
                        trigger: 'change'
                    }]<#if !formField?is_last>,
                    </#if>
                    </#if>
                    </#list>
                    </#if>
                }
            }
        ,
            columns: [
                <#if displayFields ?eval?size gt 0>
                <#list displayFields ?eval as displayField>
                {
                    prop: '${displayField.columnName}',
                    label: '${displayField.columnAlias}'
                },
                </#list>
                <#else>
                <#list table.columns as column>
                {
                    prop: '${column.columnNameLower}',
                    label: '${column.columnAlias!}'
                },
                </#list>
                </#if>
                {
                    prop: 'operate',
                    align: 'center',
                    label: '操作',
                    render: (h, params) => {
                        return h('div', {
                                style: {
                                    display: 'flex',
                                    alignItems: 'center',
                                    justifyContent: 'center'
                                }
                            },
                            [
                                h('div', {
                                    style: {
                                        color: '#005da9',
                                        padding: '0 10px',
                                        cursor: 'pointer'
                                    },
                                    on: {
                                        click: () => {
                                            this.dialogBox.idItem = params.row.id;
                                            this.editData(params);
                                        }
                                    }
                                }, '编辑'),
                                h('div', {
                                    style: {
                                        color: '#e36319',
                                        padding: '0 10px',
                                        cursor: 'pointer'
                                    },
                                    on: {
                                        click: () => {
                                            this.dialogBox.idItem = params.row.id;
                                            this.deleteData();
                                        }
                                    }
                                }, '删除')
                            ]);
                    }
                }
            ]
        }
        },
        created() {

        },
        mounted() {
            this.getDataList();
        },
        methods: {
            // 数据列表查询
            getDataList(page) {
                this.pageObj.currentPage = page === true ? this.pageObj.currentPage : 1;
                let formModel = this.formConfig.formModel
                this.$api.tableList.dataList('/${classNameLower}', {
                    page: this.pageObj.currentPage.toString(),
                    rows: this.pageObj.pageSize.toString(),
                    <#if sortFields ?eval?size gt 0>
                    <#list sortFields ?eval as sortField>
                    sortBy: '${sortField.columnName} ${sortField.sortBy}',
                    </#list>
                    </#if>
                    <#if searchFields ?eval?size gt 0>
                    <#list searchFields ?eval as searchField>
                    <#if searchField.columnSearchType == 'dateDuring'>
                    startTime: formModel.${searchField.columnName} === null || formModel.${searchField.columnName}.length === 0 ? '' : formModel.${searchField.columnName}[0],
                    endTime: formModel.${searchField.columnName} === null || formModel.${searchField.columnName}.length === 0 ? '' : formModel.${searchField.columnName}[1],
                <#else>
                ${searchField.columnName}:
                formModel.${searchField.columnName},
                </#if>
                </#list>
                </#if>

            }).
                then(res => {
                    if (res.data.code == '0000') {
                        let resData = res.data.data;
                        this.pageObj.total = resData.count;
                        this.tableData = resData.list;
                    }

                })
            },
            //新增数据
            addData(formName) {
                this.formConfigs.formModel = {
                <#if formFields ?eval?size gt 0>
                <#list formFields ?eval as formField>
                ${formField.columnName}:
                ''<#if !formField?is_last>,
                </#if>
                </#list>
                </#if>
            }
                if (this.$refs['formModels']) {
                    this.$refs['formModels'].$refs['formModels'].resetFields();
                }
                this.dialogBox.isEdit = false;
                this.dialogBox.boxShow = true;
            },
            //删除数据
            deleteData() {
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.delete('/${classNameLower}/' + this.dialogBox.idItem).then(res => {
                        if (res.data.code == '0000') {
                            this.getDataList(true);
                        } else {
                            this.$message.warning('数据删除失败');
                        }

                    })
                })
            },
            //编辑数据表单赋值
            editData(params) {
                if (this.$refs['formModels']) {
                    this.$refs['formModels'].$refs['formModels'].resetFields();
                }
                this.formConfigs.formModel = {
                <#if formFields ?eval?size gt 0>
                <#list formFields ?eval as formField>
                ${formField.columnName}:
                params.row.${formField.columnName}<#if !formField?is_last>,
                </#if>
                </#list>
                </#if>
            }
                this.dialogBox.isEdit = true;
                this.dialogBox.boxShow = true;
            },
            // 新增或编辑数据
            confirSubmit() {
                this.$refs['formModels'].$refs['formModels'].validate((valid) => {
                    if (valid) {
                        let formModel = this.formConfigs.formModel
                        if (this.dialogBox.isEdit) {
                            this.$api.tableList.dataEdit('/${classNameLower}', {
                                id: this.dialogBox.idItem,
                            <#if formFields ?eval?size gt 0>
                            <#list formFields ?eval as formField>
                            ${formField.columnName}:
                            formModel.${formField.columnName}<#if !formField?is_last>,
                            </#if>
                            </#list>
                            </#if>
                        }).
                            then(res => {
                                if (res.data.code == '0000') {
                                    this.dialogBox.boxShow = false;
                                    this.getDataList(true);
                                } else {
                                    this.$message.warning('数据编辑失败');
                                }

                            })
                        } else {
                            this.$api.tableList.dataAdd('/${classNameLower}', {
                            <#if formFields ?eval?size gt 0>
                            <#list formFields ?eval as formField>
                            ${formField.columnName}:
                            formModel.${formField.columnName}<#if !formField?is_last>,
                            </#if>
                            </#list>
                            </#if>
                        }).
                            then(res => {
                                if (res.data.code == '0000') {
                                    this.dialogBox.boxShow = false;
                                    this.getDataList();
                                } else {
                                    this.$message.warning('数据新增失败');
                                }

                            })
                        }
                    } else {
                        this.$message.closeAll();
                        this.$message.warning('信息校验失败');
                    }
                })
            },
            // 分页页数改变
            pageChange(page) {
                this.pageObj.currentPage = page;
                this.getDataList(true);
            },
            // 搜索条件重置
            resetForm(formName) {
                this.$refs[formName].$refs[formName].resetFields();
            }
        },
        components: {
            MyTable,
            MyForm
        },
        computed: {}
    }
</script>
