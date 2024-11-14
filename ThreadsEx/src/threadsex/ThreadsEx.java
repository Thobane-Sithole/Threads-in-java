package threadsex;

public class ThreadsEx {
    
    public static boolean isPaymentProcessedThread = false;
    public static boolean isOrderValidatedThread = false;
    
    public static void main(String[] args) {
        
        Thread dataThread = new DataProcessingThread();
        Thread paymentThread = new PaymentProcessingThread();
        Thread OrderValidationThread = new OrderValidationThread();
        
        dataThread.setPriority(Thread.MIN_PRIORITY);
        paymentThread.setPriority(Thread.NORM_PRIORITY);
        OrderValidationThread.setPriority(Thread.MAX_PRIORITY);
        
        dataThread.start();
        paymentThread.start();
        OrderValidationThread.start();
        
    }
    
    static class DataProcessingThread extends Thread{
        @Override
        public void run(){
            synchronized (ThreadsEx.class) {
                try{
                    System.out.println("Data Processing has started.");
                    Thread.sleep(4000);
                    System.out.println("Data Processing has finished");
                    isOrderValidatedThread = true;
                    ThreadsEx.class.notifyAll();
                    
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    
    static class PaymentProcessingThread extends Thread{
        @Override
        public void run(){
            synchronized (ThreadsEx.class) {
                
                try{
                    while (!isOrderValidatedThread) {
                        
                        System.out.println("Waiting for Data processing thread to finish.");
                        ThreadsEx.class.wait();                    
                    }
                    System.out.println("Payment Processing has started.");
                    isPaymentProcessedThread = true;
                    Thread.sleep(4000);
                    ThreadsEx.class.notifyAll();
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    
    static class OrderValidationThread extends Thread{
        @Override
        public void run(){
            synchronized (ThreadsEx.class) {
                    try{
                
                        while (!isPaymentProcessedThread) {
                            System.out.println("Waiting for the payment processing Thread.");
                            ThreadsEx.class.wait();
                        }
                        System.out.println("Order validation has started");
                        ThreadsEx.class.notifyAll();
                        
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }


