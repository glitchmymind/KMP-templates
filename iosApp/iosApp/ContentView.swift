import SwiftUI
import CommonSDK

struct ContentView: View {
    PlatformSDK.init()
    
	var body: some View {
		Text("Hello, iOS")
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
