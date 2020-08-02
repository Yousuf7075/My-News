import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Noban Hasan on August, 2020
 *
 */


object NetworkUtils {

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return false
    }
}// This class is not publicly instantiable
