package com.example.dexposed;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.taobao.android.dexposed.XposedHelpers;

public class DexPosedActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dex_posed);


    // Target class, method with parameter types, followed by the hook callback (XC_MethodHook).
    DexposedBridge.findAndHookMethod(Activity.class, "onCreate", Bundle.class, new XC_MethodHook() {

      // To be invoked before Activity.onCreate().
      @Override protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        // "thisObject" keeps the reference to the instance of target class.
        Activity instance = (Activity) param.thisObject;

        // The array args include all the parameters.
        Bundle bundle = (Bundle) param.args[0];
        Intent intent = new Intent();
        // XposedHelpers provide useful utility methods.
        XposedHelpers.setObjectField(param.thisObject, "mIntent", intent);

        // Calling setResult() will bypass the original method body use the result as method return value directly.
        if (bundle.containsKey("return"))
          param.setResult(null);
      }

      // To be invoked after Activity.onCreate()
      @Override protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        XposedHelpers.callMethod(param.thisObject, "sampleMethod", 2);
      }
    });
  }
}