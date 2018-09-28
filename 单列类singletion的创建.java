//单列类(Singleton)
//单列类只有一个实例，外部不能new, 自己管理自己
//eg:当前在线人数，别的service只能get到数据，而不能new一个新数据，不然会导致不同登陆端看到的数据不同, 实现数据共享

/**
 * Lazy mode
 * 只有当调用时，如果有对象则返回对象，没有则创建一个新对象返回
 * 初始化快，程序运行时慢
 */
public class SingletonDemo {
    private static SingletonDemo instance;
    private SingletonDemo(){

    }
    public static SingletonDemo getInstance(){
        if(instance==null){
            instance=new SingletonDemo(); 
        }
        return instance;
    }
}
/**
 * hungry mode
 * 先初始化一个initial
 * 初始化慢，加载快
 * 一般选择hungry mode
 */
public class SingletonDemo {
    private static SingletonDemo instance=new SingletonDemo();
    private SingletonDemo(){

    }
    public static SingletonDemo getInstance(){
        return instance;
    }
}

/**
 * 通过枚举创建
 */
enum SingletonDemo{
    INSTANCE;
    public void otherMethods(){
        System.out.println("Something");
    }
}
//当我们需要调用这个方法时
    SingletonDemo.INSTANCE.otherMethods();