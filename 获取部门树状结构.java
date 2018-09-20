/**
 * 获取一系列数据的树状结构
 * eg: 部门
 */

 /**
  * Controller 端
  */
  @GetMapping(value = "/tree")
  public ResultModel treeOrganization() {
      Long tenantId = SecurityUtils.getTenantId(); //获取自己的租户id, 在例子中数据库存了所有的部门，获取自身租户id后可以得到所有该公司所有部门
      TreeNode treeNode = organizationService.getTree(tenantId);
      return ResultModel.success(treeNode, "成功获取树状结构");
  }

  /**
   * Service 端
   */
  TreeNode getTree(Long tenantId);

  /**
   * IMPL端
   */
  /**
   * 将VO转成treeNode
   * 根据不同情况具体分析
   */
    private TreeNode switchTreeNode(OrganizationVO organizationVO){
        Long id = organizationVO.getId();
        Long parentId = organizationVO.getParentId();
        String name = organizationVO.getName();
        TreeNode treeNode = new TreeNode();
        treeNode.setId(id);
        treeNode.setName(name);
        treeNode.setParentId(parentId);
        return treeNode;
    }
   
  @Override
  public TreeNode getTree(Long tenantId) {
      /**
      * 获取租户id
      */
      OrganizationVO organizationVO = organizationPOMapper.getRootOrganization(tenantId);
      Long rootId = organizationVO.getId();
      /**
       * 获取租户根部门
       */
      List<OrganizationVO> children = organizationPOMapper.getAllChildren(tenantId);
      /**
       * 新建一个以node为PO的列表
       */
      List<TreeNode> nodes = new ArrayList<>();
      /**
       * 将原来的VO转换成node,可以在VO里set, 也可以在IMPL里写，这里选择在impl里完成
       */
      for (OrganizationVO organizationVO1:children) {
          nodes.add(this.switchTreeNode(organizationVO1));
      }
      /**
       * 将node set进去
       */
      treeUtils.setTreeNodeList(nodes);
      return treeUtils.generateTree(rootId);
  }

}

    /**
     * TreeNode 模型
     */
    package com.yiwise.common.model;

    import java.io.Serializable;
    import java.util.ArrayList;
    import java.util.List;

    public class TreeNode implements Serializable {

        private Long id;
        private Long parentId;
        private String name;
        private List<TreeNode> nodes = new ArrayList();//递归自己

        public TreeNode() {

        }
        /**
         * getter and setter
         */
    }

    /**
     * treeUtil (应该换个名字，不能用utils)
     */
    package com.yiwise.core.service.platform;

    import com.yiwise.common.model.TreeNode;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;
    import java.util.List;

    /**
     * tree解析
     * 将TreeNodeList set 成树状结构中的数据列表，调用generateTree,将参数设为root的id
     * @author Haokun Wang
     * @date 2018/9/4
     */
    @Service
    public class TreeGenerator {
        private List<TreeNode> treeNodeList;

        public void setTreeNodeList(List<TreeNode> treeNodeList) {
            this.treeNodeList = treeNodeList;
        }

        /**
         * 遍历treeNodeList, 节点id = parentId
         * @param parentId
         * @return 节点
         */
        public TreeNode getParentById(Long parentId){
            TreeNode treeNode = new TreeNode();
            for (TreeNode treeNode1:treeNodeList) {
                if (treeNode1.getId() == parentId) {
                    treeNode = treeNode1;
                    break;
                }
            }
            return treeNode;
        }

        /**
         * 寻找所有子节点
         * @param parentId
         * @return 子节点列表
         */
        public List<TreeNode> getChildrenById(Long parentId){
            List<TreeNode> childTreeNode = new ArrayList<TreeNode>();
            for (TreeNode treeNode : treeNodeList) {
                if (treeNode.getParentId() == parentId) {
                    childTreeNode.add(treeNode);
                }
            }
            return childTreeNode;
        }

        /**
         * 递归生成树状结构
         */
        public TreeNode generateTree(Long parentId) {
            TreeNode root = this.getParentById(parentId);
            List<TreeNode> childrenTree = this.getChildrenById(parentId);
            for (TreeNode treeNode:childrenTree) {
                TreeNode node = this.generateTree(treeNode.getId());
                root.getNodes().add(node);
            }
            return root;
        }
    }

    