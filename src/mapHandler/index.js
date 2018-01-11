import { requireNativeComponent, View, NativeModules } from 'react-native';

var iface = {
	name: 'MapView',
	propTypes: {
		...View.propTypes // include the default view properties
	}
};

const MapView = requireNativeComponent('MapView', iface, {
	nativeOnly: {
		onMapReady: true
	}
});
const MapEvent = NativeModules.MapEvent;

export { MapView, MapEvent };
