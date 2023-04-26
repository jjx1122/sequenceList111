package squenceList;

public class sequenceList<T> {
    final int maxSize=10;
    private int length=10;
    private final T[] listArray;//存储元素的数组对象

    public sequenceList(){
        length=0;//这个是顺序表的长度，要将这个跟listArray的length属性区分开。
        listArray=(T[]) new Object[maxSize];
    }
    public sequenceList(int n){
        if (n <= 0) {
            System.out.println("error");
            System.exit(1);
        }
        length = 0; // Set length to n since you want to initialize the entire array
        listArray = (T[]) new Object[n];
//        for (int i = 0; i < n; i++) {
//            listArray[i] =  0; // Cast Object to T; Initialize each element to 0
//        }
    }
    //插入元素
    public boolean insert(T obj,int n){  //n代表插入的位置
       if(n>length+1||n<1){ //判断n的值是否合法
           return false;
       }
       for (int j=length;j>=n;j--){
           listArray[j]=listArray[j-1];
       }
       listArray[n-1]=obj;//注意这里是n-1，下标和第几个元素之间相差了一个1。
       length++;
       return true;
    }
    //删除元素,通过移动元素实现
    public void delete(int pos){
        if(length==0){
            System.out.println("没有元素");
        }
        else if(pos>=1&&pos<=length){
            for(int i=pos;i<length;i++){
                listArray[i-1]=listArray[i];
            }
            length--;
        }
        else System.out.println("不合法");
    }
    //修改元素
    public void update(T obj,int pos){
        if(length==0){
            System.out.println("没有元素");
        }
        else {
            listArray[pos-1]=obj;
        }
    }
    //返回元素下标
    public int findPos(T obj){
        for(int i=0;i<length;i++){
            if(listArray[i].equals(obj)){ //equals
                return i+1;
            }
        }
        return -1;
    }
    //输出线性表
    public void display(){
        for(int i=0;i<length;i++){
            System.out.println(listArray[i]);
        }
    }

    public static void main(String[] args){
        sequenceList<Integer> list=new sequenceList<>(10);
        int[] a=new int[]{1,2,3,4,5,8,41};
        for (int i=0;i<a.length;i++){
            list.insert(a[i],i+1);
        }
        list.delete(2);
        list.update(21,2);
        list.display();
        System.out.println("8是"+list.findPos(8));
    }
}
