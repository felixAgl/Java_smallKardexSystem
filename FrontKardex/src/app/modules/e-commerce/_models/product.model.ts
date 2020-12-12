export interface IProduct {
  id?: number;
  document?: number;
  name?: string;
  quantity?: number;
  cost?: number;
  total?: number;
  detail?: string;
}
export class Product implements IProduct {
  constructor(
    public id?: number,
    public document?: number,
    public name?: string,
    public quantity?: number,
    public cost?: number,
    public total?: number,
    public detail?: string) { }
}
