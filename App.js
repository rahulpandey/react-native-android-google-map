/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import { StyleSheet, View, Button, DeviceEventEmitter } from 'react-native';
import { MapView, MapEvent as event } from './src/mapHandler';

export default class App extends Component<{}> {
	componentWillMount = () => {
		DeviceEventEmitter.addListener('onMapReady', e => {
			console.warn('Map Loaded');
			event.addMarker({
				latitude: 19.076,
				longitude: 72.8777,
				zoomToLatLong: true
			});
		});
	};

	removeMarker = () => {
		event.removeMarker({
			latitude: 19.076,
			longitude: 72.8777
		});
	};
	render() {
		return (
			<View style={styles.container}>
				<MapView style={styles.mapView} />
				<Button
					title="Remove Current Marker"
					onPress={this.removeMarker}
					style={{ alignSelf: 'center' }}
				/>
			</View>
		);
	}
}

const styles = StyleSheet.create({
	mapView: {
		position: 'absolute',
		top: 0,
		bottom: 0,
		left: 0,
		right: 0
	},
	container: {
		flex: 1,
		justifyContent: 'flex-start',
		alignItems: 'flex-start',
		backgroundColor: '#F5FCFF'
	}
});
