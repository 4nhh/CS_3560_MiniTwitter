# About
This program was created in Java as coursework for CS 3560: Object-Oriented Programming at California State Polytechnic University, Pomona.
It is an exercise of four design patterns: Singleton, Composite, Observer, and Visitor. 

![image](https://github.com/4nhh/CS_3560_MiniTwitter/assets/155146676/691cdb00-de15-41ef-b6ec-1b57930fdcac)

## Singleton
The __Singleton__ pattern was applied to the **AdminController** class. As the class controls the application's data and interactions between
the GUI and the objects, it was important that access to an ``AdminController`` would be centralized to a single instance.

- **Private instantiation**:
```
private AdminController() {        
    users = new HashMap<>();
    groups = new HashMap<>();
    userViews = new HashMap<>();
    statsViews = new HashMap<>();
    root = new Group("Root", null);
    groups.put("Root", root);
}
```
- **Public access to instance**:
```
public static AdminController getInstance() {
    if (instance == null) { instance = new AdminController(); }
    return instance;
}
```

## Composite
The __Composite__ pattern forms the basis for the __organizational hierarchy__ of groups, subgroups, and users. ``Group`` and ``User``
objects implement the same ``TwitterComponent`` interface:
```
public interface TwitterComponent {
    public void add(TwitterComponent newChild);
    public List<String> getChildren();
    public String getID();
    DefaultMutableTreeNode createTreeNode();   
}
```
The pattern allows the objects in the hierarchy to be treated similarly, *regardless of type*, which was especially useful when constructing the tree model.

- In ``Group.java``:
```
public DefaultMutableTreeNode createTreeNode() {
  DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(this.id, true);
    for (String childID : getChildren()) {
      TwitterComponent child = AdminController.getInstance().getChildFromID(childID);
         groupNode.add(child.createTreeNode());
    }
  return groupNode;
}
```
- In ``User.java``:
```
public DefaultMutableTreeNode createTreeNode() {
    return new javax.swing.tree.DefaultMutableTreeNode(this.id, false);
}
```

## Observer
The __Observer__ pattern was used in the implementation of follower/following relationships. When one user follows another,
the followee is notified so that the user may be added to the list of followers. When a user sends a tweet, ``update(Observable, Object)``,
from the ``Observer`` class, is called on all followers in order to update their news feed and ``lastUpdateTime``.
```
public void update(Observable obj, Object arg) {
    if (arg instanceof Tweet) {
        newsfeed.add((Tweet)arg);
        lastUpdateTime = ((Tweet)arg).getCreationTime();
    }
}
```
![image](https://github.com/4nhh/CS_3560_MiniTwitter/assets/155146676/15358b67-4cfb-4e97-a738-f944476e4fba)

## Visitor
The __Visitor__ pattern was used to collect analytics about ``Group``, ``User``, and ``Tweet`` objects.
Classes ``GroupVisitor``, ``UserVisitor``, and ``TweetVisitor`` all implement the same ``TwitterVisitor`` interface.
```
public interface TwitterVisitor {
    void visit(User user);
    void visit(Group group);
    void visit(Tweet tweet);
}
```

![image](https://github.com/4nhh/CS_3560_MiniTwitter/assets/155146676/e9c3ca2d-a0c9-44cc-9a24-accef5431f7d)
