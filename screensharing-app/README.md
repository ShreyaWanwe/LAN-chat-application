# Screensharing App

## Overview
This project is a screensharing application built using TypeScript and Node.js. It leverages WebRTC for real-time communication and Express for handling HTTP requests. The application allows users to initiate and terminate screensharing sessions seamlessly.

## Features
- Start and stop screensharing sessions
- Real-time communication using WebRTC
- Easy integration with Express routes
- Modular architecture with controllers, services, and utilities

## Tech Stack
- **Backend**: Node.js, Express
- **Real-time Communication**: WebRTC
- **TypeScript**: For type safety and better development experience

## Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```
   cd screensharing-app
   ```

3. Install the dependencies:
   ```
   npm install
   ```

## Usage

1. Start the application:
   ```
   npm start
   ```

2. Access the application in your browser at `http://localhost:3000`.

## API Endpoints
- **POST /screenshare/start**: Initiates a screensharing session.
- **POST /screenshare/stop**: Terminates the current screensharing session.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.