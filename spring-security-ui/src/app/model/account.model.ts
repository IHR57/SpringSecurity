
export class Account {

  public customerId: number;
  public accountNumber: number;
  public accountType: string;
  public branchAddress: string;
  

  constructor(customerId?: number,accountNumber?: number,accountType?: string, branchAddress?: string){
        this.customerId = customerId || -1;
        this.accountNumber = accountNumber || -1;
        this.accountType = accountType || "";
        this.branchAddress = branchAddress || "";
  }

}