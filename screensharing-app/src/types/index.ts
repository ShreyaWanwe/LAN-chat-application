export interface ScreenshareRequest {
    userId: string;
    sessionId: string;
}

export interface ScreenshareResponse {
    success: boolean;
    message: string;
    sessionId?: string;
    streamUrl?: string;
}