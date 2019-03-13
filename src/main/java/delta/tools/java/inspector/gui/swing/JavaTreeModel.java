package delta.tools.java.inspector.gui.swing;

import javax.swing.tree.DefaultTreeModel;

/**
 * Swing tree model that reflects some Java entities.
 * @author DAM
 */
public class JavaTreeModel extends DefaultTreeModel
{
  private static final long serialVersionUID=1L;

  /**
   * Constructor.
   */
  public JavaTreeModel()
  {
    super(null);
  }

  @Override
  public Object getRoot()
  {
    return null;
  }

  @Override
  public Object getChild(Object parent, int index)
  {
    return null;
  }

  @Override
  public int getChildCount(Object parent)
  {
    return 0;
  }

  @Override
  public boolean isLeaf(Object node)
  {
    return true;
  }

  @Override
  public int getIndexOfChild(Object parent, Object child)
  {
    return 0;
  }
}
