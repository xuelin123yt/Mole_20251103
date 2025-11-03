package tw.edu.pu.csim.tcyang.mole

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MoleViewModel : ViewModel(){
    var counter by mutableLongStateOf(0)
        private set  //表示只有 ViewModel 內部可以修改它

    fun incrementCounter() {
        counter++
    }
}
