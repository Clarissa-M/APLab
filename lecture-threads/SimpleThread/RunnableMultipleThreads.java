public class RunnableMultipleThreads {
	private static class PointlessPrint implements Runnable {
		private int threadNum;
		private int n;
		public PointlessPrint(int threadNum,int n) {
			this.threadNum = threadNum;
			this.n = n;
		}
		public void run() {
			for(int i=0;i<n;i++) {
				System.out.println(ANSI_COLORS[threadNum+1] + 
					i + "/" + n + " I am thread #" + threadNum + 
					ANSI_COLORS[0]);
			}
		}
	}
	public static void main(String[] args) {
		int nThreads = 2;
		Thread[] threads = new Thread[nThreads];
		for(int i=0; i<nThreads; i++)
		{
			threads[i] = new Thread(new PointlessPrint(i,10));
			threads[i].start();
		}
	}
	public static final String[] ANSI_COLORS = {"\u001B[0m", "\u001B[32m", "\u001B[33m"}; //first is reset
}