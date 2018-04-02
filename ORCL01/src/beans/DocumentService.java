package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import domain.Document;
 
@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService {
     
    public TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Document("Files", "-", "Folder"), null);
         
        TreeNode tables = new DefaultTreeNode(new Document("Tables", "-", "Folder"), root);
         
        TreeNode countries = new DefaultTreeNode(new Document("COUNTRIES", "-", "Folder"), tables);
        TreeNode departments = new DefaultTreeNode(new Document("DEPARTMENTS", "-", "Folder"), tables);
        TreeNode employees = new DefaultTreeNode(new Document("EMPLOYEES", "-", "Folder"), tables);
        TreeNode jobs = new DefaultTreeNode(new Document("JOBS", "-", "Folder"), tables);
        TreeNode jobhistory = new DefaultTreeNode(new Document("JOB_HISTORY", "-", "Folder"), tables);
        TreeNode locations = new DefaultTreeNode(new Document("LOCATIONS", "-", "Folder"), tables);
        TreeNode regions = new DefaultTreeNode(new Document("REGIONS", "-", "Folder"), tables);
         
        return root;
    }
}
