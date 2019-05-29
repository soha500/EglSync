public class/*sync _5eSyYHe_EemrVZuAyqFicw name  */  new1 Window /*endSync */{
	
	protected String state = "open";
	public  temp;
	
	public void update() {
		if (state.equalsIgnoreCase("open")) {
			return;
		}
		if (state.equalsIgnoreCase("closed")) {
			return;
		}
		public void open() {
			this.state = "open";
			//sync _6Xcn8He_EemrVZuAyqFicw action     
			open action
			//endSync
			
			
			/* protected region open on begin */
			 System.out.println("I am in open state");
			/* protected region open end */


	}
		public void closed() {
			this.state = "closed";
			//sync _65N4QHe_EemrVZuAyqFicw action     
			new1 closed
			//endSync
			
			
			/* protected region closed on begin */
			 System.out.println("I am in closed state");
			/* protected region closed end */


	}
	
}
