The ThreadsEx project demonstrates the usage of multithreading and thread synchronization in Java. It consists of three threads that simulate a simple order processing system. The threads run in a specific order, where each thread waits for the previous one to finish before starting. The threads communicate using Java's wait() and notifyAll() methods.

The three threads in the program are:

DataProcessingThread: Handles data processing.
PaymentProcessingThread: Handles payment processing.
OrderValidationThread: Handles order validation.
Each thread is assigned a priority, and they are synchronized to ensure they execute in the correct order. The program simulates the process of data processing, payment processing, and order validation, with each step waiting for the previous one to complete.

Table of Contents
Key Features
How It Works
Code Example
Requirements
How to Run
Output Example
License
Key Features
Multithreading: Utilizes multiple threads to simulate a real-world process involving data, payment, and order validation.
Thread Synchronization: Uses synchronized blocks, wait(), and notifyAll() to manage thread execution order.
Thread Priorities: Threads are given different priorities to control their execution order:
DataProcessingThread: Low priority
PaymentProcessingThread: Normal priority
OrderValidationThread: High priority
How It Works
1. Data Processing Thread
Description: This thread starts first, processing data for 4 seconds.
Action: It then signals the next thread (PaymentProcessingThread) that it has finished.
2. Payment Processing Thread
Description: This thread waits for the DataProcessingThread to finish before starting.
Action: Once it starts, it processes the payment for 4 seconds and signals the OrderValidationThread to begin.
3. Order Validation Thread
Description: This thread waits for the PaymentProcessingThread to finish before starting.
Action: Once it starts, it validates the order and completes the process.
Synchronization Mechanisms
All threads use a synchronized block on the ThreadsEx.class object to manage access to shared resources.
The wait() method is used to pause the execution of a thread until the condition (completion of the previous thread) is met.
The notifyAll() method is used to wake up all threads that are waiting.
Thread Priorities
DataProcessingThread: Thread.MIN_PRIORITY (Lowest priority)
PaymentProcessingThread: Thread.NORM_PRIORITY (Normal priority)
OrderValidationThread: Thread.MAX_PRIORITY (Highest priority)
