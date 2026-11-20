# GitHub Actions 入门示例（配 pytest）

一条最小但真实的流水线：`push` / `PR` → 装环境 → lint → 跑 pytest → 上传测试报告。
另外带一个 `workflow_dispatch` 手动触发的示例。

## 目录结构

```
.github/workflows/ci.yml        主流水线（push / PR 触发）
.github/workflows/manual.yml    手动触发示例
src/calc.py                     被测代码
tests/test_calc.py              pytest 用例
requirements.txt / pytest.ini   依赖与 pytest 配置
```

## 本地先跑通，再上流水线

```bash
python -m pip install -r requirements.txt
pytest
ruff check .
```

本地能过，流水线才有意义——先排除「代码问题」和「CI 配置问题」混在一起。

## 怎么把它用起来

1. 新建一个 GitHub 仓库（public 最省事，私有仓库每月有免费额度）。
2. 把这个文件夹里的内容（包括 `.github` 隐藏目录）提交并 push 到 `main`。
3. 打开仓库 → **Actions** 标签页，能看到 `CI` 已经在跑。
4. 点进某次运行，展开每个 step 看日志；下载 Artifacts 里的 `test-report-*`。
5. 故意把 `tests/test_calc.py` 里某个断言改错，push 一次，观察失败长什么样，
   以及 `always()` 为什么仍然上传了报告。

## 核心概念对照（先记这 7 个）

| 概念 | 作用 | 本示例中的位置 |
|---|---|---|
| `on` | 什么时候触发 | `push` / `pull_request` / `workflow_dispatch` |
| `jobs` | 一个流水线里的任务集合，默认并行 | `test`、`hello` |
| `runs-on` | 用哪种机器跑（GitHub 提供的 runner） | `ubuntu-latest` |
| `steps` | 一个 job 内的顺序步骤 | 拉代码 → 装 Python → 装依赖 → lint → 测试 → 上传 |
| `uses` | 复用别人写好的 Action | `actions/checkout@v4` 等 |
| `run` | 执行 shell 命令 | `pytest --junitxml=reports/junit.xml` |
| `with` / `${{ }}` | 给 Action 传参 / 引用表达式 | 版本矩阵、`if: always()` |

## 下一步练习（按顺序）

1. 加一个 `deploy` job，用 `needs: test` 让它只在测试通过后跑。
2. 用 `actions/cache` 手动缓存一个目录，理解 cache key 的命中逻辑。
3. 把测试报告换成 Allure，用 `actions/upload-artifact` + GitHub Pages 发布。
4. 加 `concurrency` 组，让同一分支的新 push 自动取消上一次运行。
5. 把仓库设置里的 `main` 分支保护打开，要求 CI 通过才能合并——这就是「质量门禁」。
