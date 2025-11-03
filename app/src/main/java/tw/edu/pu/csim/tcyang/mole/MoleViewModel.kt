package tw.edu.pu.csim.tcyang.mole

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoleViewModel : ViewModel() {

    // 計數器，用來記錄用戶的點擊次數
    var counter by mutableLongStateOf(0)
        private set  // 只有 ViewModel 內部可以修改它

    // 計時器，用來從0開始計時
    var stay by mutableLongStateOf(0)
        private set  // 只有 ViewModel 內部可以修改它

    // 螢幕範圍的邊界
    var maxX by mutableStateOf(0)
        private set

    var maxY by mutableStateOf(0)
        private set

    // 用來記錄地鼠的偏移位置
    var offsetX by mutableStateOf(0)
        private set

    var offsetY by mutableStateOf(0)
        private set

    // 是否停止遊戲
    var isGameOver by mutableStateOf(false)
        private set

    init {
        startCounting() // 開始計時
    }

    // 增加 counter 的值
    fun incrementCounter() {
        if (!isGameOver) {  // 當遊戲結束時，點擊不再加分
            counter++
        }
    }

    // 啟動協程，每秒增加一次 stay 的值，並隨機移動地鼠
    private fun startCounting() {
        viewModelScope.launch {
            while (!isGameOver && stay < 60) { // 當時間未到60秒
                delay(1000L)  // 延遲 1 秒
                stay++ // 計數器加 1，這會自動觸發 UI 更新
                moveMole() // 每秒隨機移動地鼠
            }
            endGame()  // 60秒後結束遊戲
        }
    }

    // 根據螢幕寬度、螢幕高度及地鼠圖片大小計算螢幕範圍
    fun getArea(gameSize: IntSize, moleSize: Int) {
        maxX = gameSize.width - moleSize
        maxY = gameSize.height - moleSize
    }

    // 隨機移動地鼠，不會超出螢幕範圍
    fun moveMole() {
        if (!isGameOver) {
            offsetX = (0..maxX).random()  // 隨機位置X
            offsetY = (0..maxY).random()  // 隨機位置Y
        }
    }

    // 停止遊戲
    private fun endGame() {
        isGameOver = true
    }
}
