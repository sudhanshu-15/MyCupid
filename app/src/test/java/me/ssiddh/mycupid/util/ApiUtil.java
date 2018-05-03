package me.ssiddh.mycupid.util;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

import me.ssiddh.mycupid.data.model.Data;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUtil {
    public static <T> FakeCall<Data> successCall(T data) {
        FakeCall<Data> fakeCall = new FakeCall(Response.success(data), null);
        return fakeCall;
    }

    static final class FakeCall<T> implements Call<T> {

        private final Response<T> response;
        private final AtomicBoolean canceled = new AtomicBoolean();
        private final AtomicBoolean executed = new AtomicBoolean();
        private final Throwable error;

        FakeCall(Response<T> response, @Nullable Throwable error){
            this.response = response;
            this.error = error;
        }

        @Override
        public Response<T> execute() throws IOException {
            if (response != null) return response;
            throw new IOException("Failed");
        }

        @Override
        public void enqueue(Callback<T> callback) {
            if (callback == null) {
                throw new NullPointerException("callback == null");
            }
            if (!executed.compareAndSet(false, true)) {
                throw new IllegalStateException("Already executed");
            }
            if (canceled.get()) {
                callback.onFailure(this, new IOException("canceled"));
            } else if (response != null) {
                callback.onResponse(this, response);
            } else {
                callback.onFailure(this, error);
            }
        }

        @Override
        public boolean isExecuted() {
            return false;
        }

        @Override
        public void cancel() {

        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Override
        public Call<T> clone() {
            return null;
        }

        @Override
        public Request request() {
            return null;
        }
    }

}
