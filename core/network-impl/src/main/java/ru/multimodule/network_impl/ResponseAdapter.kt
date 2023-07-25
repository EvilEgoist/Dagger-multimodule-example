import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import ru.multimodule.network_impl.NetworkCall
import java.lang.reflect.Type

class ResponseAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<S, Call<ru.multimodule.network_api.ServerResponse<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<ru.multimodule.network_api.ServerResponse<S, E>> {
       return NetworkCall(call, errorBodyConverter)
    }

}