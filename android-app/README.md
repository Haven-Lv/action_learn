# HelloAPK —— 完全在 GitHub Actions 上构建的安卓应用

源码只有一个 Activity，界面用 Java 代码直接搭（不依赖 AndroidX/Compose 等任何第三方库），
所以云端构建只需要 Android SDK 本体，速度快、失败因素少。

## 分支与流水线

- 分支：`android-hello`
- 流水线：[`.github/workflows/android.yml`](../.github/workflows/android.yml)
- 触发：推送到 `android-hello` 且改动了 `android-app/` 或 workflow 文件，或手动 `workflow_dispatch`

## 怎么拿到 APK

1. 打开仓库 **Actions** → 选择 **Android Build** 最新一次运行；
2. 页面底部 **Artifacts** → 下载 `hello-apk-debug.zip`；
3. 解压得到 `app-debug.apk`，传到手机上安装（需要允许"安装未知来源应用"）。

> debug 包用的是 Android SDK 自动生成的调试签名，可以直接装到手机，但不能上架应用商店。
> 需要正式签名时，用 `secrets` 保存 keystore，在 `buildTypes.release` 里配置签名。

## 本地构建（可选，本流程不依赖它）

```bash
cd android-app
gradle assembleDebug
# 产物：app/build/outputs/apk/debug/app-debug.apk
```
