import java.util.Arrays;
import java.util.List;

public class Filterjava{
 public static void main(String[] args) {
List<Integer> nums = Arrays.asList(10, 15, 22, 31, 40);
boolean n = nums.stream().noneMatch(a -> a<0);
System.out.println(n);



  
 


    }
}