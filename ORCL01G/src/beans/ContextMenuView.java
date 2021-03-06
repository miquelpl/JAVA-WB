package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
 
@ManagedBean(name="treeContextMenuView")
@ViewScoped
public class ContextMenuView implements Serializable {
     
	private static final long serialVersionUID = 1L;

	private TreeNode root;
    private TreeNode selectedNode;
     
    @ManagedProperty("#{documentService}")
    private DocumentService service;

	@ManagedProperty(value="#{genericTableBean}")
	private GenericTableBean genericTableBean;
	
	@PostConstruct
    public void init() {
        root = service.createDocuments();
    }
 
    public TreeNode getRoot() {
        return root;
    }
 
    public TreeNode getSelectedNode() {
        return selectedNode;
    }
 
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
 
    public void setService(DocumentService service) {
        this.service = service;
    }
     
    public void displaySelectedSingle() {
        if(selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
            //System.out.println("displaySelectedSingle " + selectedNode.getData().toString());
        }
    }
    public void onNodeSelect(NodeSelectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
        setClassNameGenericTableBean(event.getTreeNode().toString());
    }      

    public GenericTableBean getGenericTableBean() {
    	return genericTableBean;
    }
    
    public void setGenericTableBean(GenericTableBean genericTableBean) {
    	this.genericTableBean = genericTableBean;
    }

    public void setClassNameGenericTableBean(String className) {
    	this.genericTableBean.setClassName(className);
    	this.genericTableBean.init();
    }
}

