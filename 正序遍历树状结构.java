//遍历树
//正序遍历
//用的树结构来自"获取部门树状结构.java"


/**
 * Controller端
 */
@GetMapping(value = "/listOrganization")
public ResultModel listOrganization() {
    Long tenantId = SecurityUtils.getTenantId();
    List<TreeNode> initialization = organizationService.resetNewList();
    List<TreeNode> simplePageInfo = organizationService.queryOrganization(tenantId, initialization);
    return ResultModel.success(simplePageInfo,"成功获取组织列表");
}
/**
 * ServiceImpl 端
 */
private List<TreeNode> getOrganizationList(TreeNode treeNode, List<TreeNode> initialization) {
    //treeNode 参数是树状结构生成的，initialization是一个空的List
    //因为list不应该进入缓存，在Controller新调用一个service返回一个空的list

    //如果一个节点有子节点
    if (treeNode.getNodes() != null) {
        //获取这个节点除了子节点外的数据
        TreeNode treeNode1 = new TreeNode.Builder().setId(treeNode.getId()).setName(treeNode.getName()).build();
        //将该数据加入list
        initialization.add(treeNode1);
        //获取自己的子节点list
        List<TreeNode> treeNodeList = treeNode.getNodes();
        //遍历自己的字节带你list
        for (TreeNode treeNode2 : treeNodeList) {
            //递归
            getOrganizationList(treeNode2,initialization);
        }
        //当所有的子节点都没有子节点时，递归退出
    } else {
        initialization.add(treeNode);

    }
    return initialization;
}
