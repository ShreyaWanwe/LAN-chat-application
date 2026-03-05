import { Request, Response } from 'express';
import { ScreenshareService } from '../services/screenshareService';

export class ScreenshareController {
    private screenshareService: ScreenshareService;

    constructor() {
        this.screenshareService = new ScreenshareService();
    }

    public startScreenshare(req: Request, res: Response): void {
        const screenshareId = this.screenshareService.startScreenshare();
        res.status(200).json({ screenshareId });
    }

    public stopScreenshare(req: Request, res: Response): void {
        const { screenshareId } = req.body;
        this.screenshareService.stopScreenshare(screenshareId);
        res.status(200).json({ message: 'Screenshare stopped successfully' });
    }
}