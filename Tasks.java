import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tasks{
    public static void main(String[] args) {
        BlockingQueue<Task> queue = new LinkedBlockingQueue<>(2);
        List<Task> takeList = new ArrayList<>();
        Task t1 = new Task("send email");
        Task t2 = new Task("Generate Report");
        Task t3 = new Task("Process Payment");
        Task t4 = new Task("resize Image");
        Task t5 = new Task("Send Notification");
        Task stop1= new Task("STOP");
        Task stop2 = new Task("STOP");
        Task stop3 = new Task("STOP");


        takeList.add(t1);
        takeList.add(t2);
        takeList.add(t3);
        takeList.add(t4);
        takeList.add(t5);
        takeList.add(stop1);
        takeList.add(stop2);
        takeList.add(stop3);
        

        Producer p = new Producer(queue, takeList);

        Worker w1 = new Worker(queue);
        Worker w2 = new Worker(queue);
        Worker w3 = new Worker(queue);
        Thread producerThread = new Thread(p);
        Thread workerThread1 = new Thread(w1);
        Thread workerThread2 = new Thread(w2);
        Thread workerThread3 = new Thread(w3);

         producerThread.start();
         workerThread1.start();
         workerThread2.start();
         workerThread3.start(); 
         workerThread3.start();

        
      
        
       
      
        }
}

class Producer implements Runnable{
    //produces task;
    private BlockingQueue<Task> tasks = new LinkedBlockingQueue<>();
    private List<Task> task;

    public Producer(BlockingQueue<Task> tasks,List<Task> task){
            this.tasks = tasks;
            this.task = task;
    }
  @Override
public void run() {
    try {
        for (Task t : task) {
            tasks.put(t);
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}


}


class Worker implements Runnable{
private BlockingQueue<Task> tasks;
   
    public Worker(BlockingQueue<Task> tasks){
        this.tasks = tasks;
       
    }



@Override
public void run() {
    try {
        while (true) {
            Task t = tasks.take();
            if(t.name.equals("STOP")){
                break;
            }
            t.execute();
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

}

class Task {
    String name;

    public Task(String name){
        this.name = name;
    }

  void execute() {

    System.out.println(
        Thread.currentThread().getName() +
        " started " + name
    );

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.out.println(
        Thread.currentThread().getName() +
        " finished " + name
    );
}

  
   
}

