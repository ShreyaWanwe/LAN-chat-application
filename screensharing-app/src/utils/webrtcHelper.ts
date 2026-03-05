import { PeerConnection, IceCandidate } from 'wrtc';

export function createPeerConnection(onIceCandidate: (candidate: IceCandidate) => void) {
    const peerConnection = new PeerConnection();

    peerConnection.onicecandidate = (event) => {
        if (event.candidate) {
            onIceCandidate(event.candidate);
        }
    };

    return peerConnection;
}

export function handleICECandidate(peerConnection: RTCPeerConnection, candidate: IceCandidate) {
    peerConnection.addIceCandidate(candidate)
        .catch((error) => {
            console.error('Error adding received ICE candidate', error);
        });
}