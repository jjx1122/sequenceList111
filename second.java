//合并两个有序列表
package squenceList;

public class sequenceList<T> {
    final int maxSize=100;
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

    // get 方法，接受一个整数索引并返回相应的元素
    public T get(int index) {
        if (index >= 0 && index < length) { // 确保索引在有效范围内
            return listArray[index]; // 返回相应位置的元素
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index); // 如果索引无效，抛出异常
        }
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

    //合并两个有序顺序表
    public static sequenceList<Integer> merge(sequenceList<Integer> a,sequenceList<Integer> b){
        sequenceList<Integer> c=new sequenceList<>();
        int i=0;
        int j=0;
        int k=0;
        while(i<a.length&&j<b.length){
            if(a.get(i)<=b.get(j)){
                c.insert(a.get(i),k+1);
                i++;
            }
            else {
                c.insert(b.get(j),k+1);
                j++;
            }
            k++;//更新k的值
        }
        while (i<a.length){
            c.insert(a.get(i),k+1);
            i++;
            k++;
        }
        while (j< b.length){
            c.insert(b.get(j),k+1);
            j++;
            k++;
        }
        return c;
    }
    //2023.04.27。这个合并有序链表注意：1，数据k的更新 2，之前使用a.listArray[i]这种方式访问不可以，必须使用一个get方法获取里面的私有数组元素。因为被声明为private。
    //如果 merge 方法是放在 sequenceList 类中的，那么你可以直接访问 listArray 和 length，因为它们是同一个类的成员。根据您之前的描述，您在访问 a.listArray[i] 和 b.listArray[j] 时遇到了问题。
    // 在这种情况下，请注意，尽管 merge 方法在 sequenceList 类中，但 a 和 b 是 sequenceList 类的两个不同实例，因此无法直接访问它们的 private 成员。
    //所以，你仍然需要使用访问器方法（getters）来访问这些属性。请根据我之前的回答中给出的示例修改 merge 方法和 sequenceList 类

    public static void main(String[] args){
        sequenceList<Integer> list1=new sequenceList<>(10);
        int[] a=new int[]{1,2,3,4,5,8,41};
        for (int i=0;i<a.length;i++){
            list1.insert(a[i],i+1);
        }
        sequenceList<Integer> list2=new sequenceList<>(10);
        int[] b=new int[]{6,7,9,42};
        for (int i=0;i<b.length;i++){
            list2.insert(b[i],i+1);
        }
        sequenceList<Integer> list3=sequenceList.merge(list1,list2);
        list3.display();
        //System.out.println(list1.length);
//        list.delete(2);
//        list.update(21,2);
//        System.out.println("8是"+list.findPos(8));
    }
}
