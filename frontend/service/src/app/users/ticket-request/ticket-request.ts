export interface TicketRequest {
    ticketType: number;
    ticketCreateBy?: number;
    ticketDescription: string;
    ticketCreatedDate: Date;
    ticketEditedDate?:Date;
    ticketStatus: string;
};

export interface GetTicket {
    ticketStatus: string;
    ticketCreateBy: number;
}
