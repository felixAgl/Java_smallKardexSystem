export interface ISale {
    id: number;
    refProduct: number;
    name: string;
    detail: string;
    quantity: number;
    cost: number;
    total: number;
}

export class Sale implements ISale {
    constructor(
        public id: number,
        public refProduct: number,
        public name: string,
        public detail: string,
        public quantity: number,
        public cost: number,
        public total: number) { }
}

