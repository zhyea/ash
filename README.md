# Ash Framework  

一个极简的IOC框架。ash意为微尘，即微不足道。  

此项目仅为练手而用。

# 示例代码  

通过几段代码简单演示下ash-framework的使用。

创建一个Component类：

```java
package org.chobit.ash.test.compant;

import org.chobit.ash.annotation.Component;

@Component
public class MyComponent {

    public String result() {
        return "The component result";
    }
}
```

创建一个Service类, 在Service类中引用并注入了Component类的实例。：

```java
package org.chobit.ash.test.service;

import org.chobit.ash.annotation.Autowired;
import org.chobit.ash.annotation.Service;

@Service
public class MyService {

    @Autowired
    private MyComponent component;

    public String service() {
        System.out.println("This is a service!");
        return component.result();
    }

}
```

创建场景类，实现注入并调用：

```java
package org.chobit.ash.test;

import org.chobit.ash.boot.AshBootstrap;
import org.chobit.ash.helper.BeanHelper;
import org.chobit.ash.test.service.MyService;

public class MyApp {
    
    public static void main(String[] args) {
        new AshBootstrap(MyApp.class).run();
        MyService myService = BeanHelper.getBean(MyService.class);
        System.out.println(myService.service());
    }

}
```

执行结果如下：
```text
This is a service!
The component result
```

能做的事情也就这样了。

# 其他

拖延好久写完以后，才意识到最早提出ioc创意的人也许是个极端的代码洁癖患者。  

就这样。