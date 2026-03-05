import { Router } from 'express';
import ScreenshareController from '../controllers/screenshareController';

const router = Router();
const screenshareController = new ScreenshareController();

export function setScreenshareRoutes(app) {
    app.use('/screenshare', router);
    router.post('/start', screenshareController.startScreenshare.bind(screenshareController));
    router.post('/stop', screenshareController.stopScreenshare.bind(screenshareController));
}