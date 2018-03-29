import java.util.Comparator;

public class StudentTreeSet {

  private int size;
  private TreeNode root;
  private StudentComparator comparator = new StudentComparator();

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
      //TODO: implement this
      return 0;
    }
  }

  public boolean remove(Student s) {
    //TODO: implement this
    return false;
  }
}
