import java.util.Comparator;
//Jacob McAfoos
public class StudentTreeSet {

  private int size;
  private TreeNode root;
  private StudentComparator comparator = new StudentComparator();

  public StudentTreeSet(){
    this.size = 0;
    root = null;
  }

  public int size() {
    return size;
  }

  public void add(Student value) {
    if (value == null) {
      throw new UnsupportedOperationException("Can't add null student.");
    }
    if (root == null) {
      root = new TreeNode(value);
      size++;
    } else {
      add(value, root);
    }
  }

  public boolean contains(Student value) {
    if (value == null) {
      return false;
    }
    return contains(value, root);
  }

  private boolean contains(Student value, TreeNode node) {
    if (node != null) {
      int c = comparator.compare(value, node.value);
      if (c == 0) {
        return true;
      } else if (c < 0) {
        return contains(value, node.left);
      } else {
        return contains(value, node.right);
      }
    }
    return false;
  }

  private void add(Student value, TreeNode node) {
    int c = comparator.compare(value, node.value);
    if (c == 0) {
      node.value = value;
    } else if (c < 0) {
      if (node.hasLeftChild()) {
        add(value, node.left);
      } else {
        node.left = addTreeNode(value);
      }
    } else {
      if (node.hasRightChild()) {
        add(value, node.right);
      } else {
        node.right = addTreeNode(value);
      }
    }
  }

  private TreeNode addTreeNode(Student value) {
    size++;
    return new TreeNode(value);
  }

  private class TreeNode {
    private TreeNode left;
    private TreeNode right;
    private Student value;

    public TreeNode(Student value) {
      this.value = value;
    }

    public boolean hasLeftChild() {
      return left != null;
    }

    public boolean hasRightChild() {
      return right != null;
    }

    public void removeLeftChild() {
      left = null;
    }

    public void removeRightChild() {
      right = null;
    }

    public int countChildren() {
      int result = 0;
      if (hasLeftChild()) {
        result++;
      }
      if (hasRightChild()) {
        result++;
      }
      return result;
    }
  }

  private class StudentComparator implements Comparator<Student> {
    public int compare(Student left, Student right) {
      //make the students strings
      String sl = left.toString();
      String sr = right.toString();
      int count = 0;
      //make sure that there are no exceptions in for loop
      if (sl.length() < sr.length())
        count = sl.length();
      else
        count = sr.length();
      //check alphabetical order for the strings
      for (int i = 0; i < count; i++){
        if (sl.charAt(i) < sr.charAt(i))
          return -1;
        else if (sl.charAt(i) > sr.charAt(i))
          return 1;
      }
      //if same alphabetically but different lengths, the shorter one is smaller
      if (sl.length() < sr.length())
        return -1;
      else if (sl.length() > sr.length())
        return 1;
      //same size and value
      return 0;
    }
  }
//--------------------------------------------------------------------------------------------------------------------
  public boolean remove(Student s) {
    //if root.value == null
    if (root.value == null){
      return false;
    }
    //if root.value == s
    if (comparator.compare(s, root.value) == 0){
      TreeNode tempRoot = new TreeNode(null);
      tempRoot.left = root;
      boolean result = remove(root, s, tempRoot);
      root = tempRoot.left;
      return result;
    }
    return remove(root, s, null);
  }
//--------------------------------------------------------------------------------------------------------------------
  private boolean remove(TreeNode n, Student s, TreeNode parent){
    //if  s < n.value
    if (comparator.compare(s, n.value) < 0){
      if (n.left != null)
        return remove(n.left, s, n);
      else
        return false;
    } else if (comparator.compare(s, n.value) > 0){//if s > n.value
      if (n.right != null)
        return remove(n.right, s, n);
      else
        return false;
    } else{ //if s == n.value
      if (n.left != null && n.right != null){//2 children
        n.value = minValue(n.right);
        remove(n.right, n.value, n);
      }else if(n.left != null){//1 child (left)
        n.value = maxValue(n.left);
        remove(n.left, n.value, n);
      }else if(n.right != null){//1 child right
        n.value = minValue(n.right);
        remove(n.right, n.value, n);
      }else{//no children
        if (parent.left != null && comparator.compare(parent.left.value, n.value) == 0){
          parent.removeLeftChild();
        }else if (parent.right != null && comparator.compare(parent.right.value, n.value) == 0){
          parent.removeRightChild();
        }
      }
      return true;
    }
  }
//---------------------------------------------------------------------------------------------------------------------
  public Student minValue(TreeNode n){
    if (n.left == null)
      return n.value;
    else
      return minValue(n.left);
  }
  public Student maxValue(TreeNode n){
    if (n.right == null)
      return n.value;
    else
      return minValue(n.right);
  }
}

//----------------------------------------------------------------------------------------------------------------------
