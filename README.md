# Hotel Helper KV 数据库项目文档

## 一、项目概述
### 项目名称
Hotel Helper KV 数据库

### 项目描述
Hotel Helper KV 数据库是一个专为酒店信息管理设计的键值（Key - Value）数据库系统。它能够高效地存储和管理酒店的各类数据，如客房信息、客户信息、预订记录等，为酒店的日常运营和管理提供强大的数据支持。

## 二、目录
1. [项目背景](#项目背景)
2. [项目结构](#项目结构)
3. [安装与配置](#安装与配置)
4. [使用方法](#使用方法)
5. [代码示例](#代码示例)
6. [常见问题解答](#常见问题解答)


## 三、项目背景
自从父亲的民宿接入"旅馆小帮手"系统后，订单数据就像被哈士奇啃过的账本。张阿姨预定的山景房显示成"后厨洗碗工位"，客人寄存的行李箱在系统里凭空消失。小爪一查代码，发现所有数据都被胡乱无序地塞进十几个txt文件里。

而且小爪有时候发现不在家的时候如果也能工作，不就更方便了吗？为此他去了解了网络传输的基本原理，学习socket编程技术，成功让自己在外出出差的时候也能够工作。

正巧，小爪还学了一门叫做数据结构的课程。这里讲授了很多种存储结构，他发现了一个神奇的结构----哈希表。它是一种k-v结构，其中，key可以用来标识这个数据，value用来存储具体数据。想到这里，小爪很兴奋，他有了更好的想法：既然key可以标识一个数据，那也可以用来标识一组相同类型的数据；，那再封装一下，不还可以变成队列(先进先出)和栈(后进先出)吗？

见识到哈希表的灵活与精准，小爪当即决定"用哈希表重写！"小爪连夜给每个客人分配唯一ID当key，订单数据用队列存，特色服务用栈存。结果凌晨三点系统突然抽风——301房客人点"叫早服务"时，屏幕弹出刺眼的NullPointerException，机器人管家对着空房间喊了半小时"早上好"，把新入住的客人吓到报警。

而且记性不好的他居然将之前学过的集合框架都忘了，去哪找可以左进右进，左出右出的数据结构呢？

你能帮小爪设计个防手残的网络key-value数据库吗？至少别再让机器人对着空气说"祝您入住愉快"了！

## 四、项目结构
```plaintext
HotelHelperKV/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── Hrup/
│   │   │           └── HotelHelper/
│   │   │               ├── database/
│   │   │               │   ├── KVDatabase.java
│   │   │               │   └── DatabaseManager.java
│   │   │               ├── model/
│   │   │               │   ├── Room.java
│   │   │               │   ├── Customer.java
│   │   │               │   └── Reservation.java
│   │   │               ├── service/
│   │   │               │   ├── RoomService.java
│   │   │               │   ├── CustomerService.java
│   │   │               │   └── ReservationService.java
│   │   │               └── util/
│   │   │                   ├── ConfigLoader.java
│   │   │                   └── Logger.java
│   │   └── resources/
│   │       └── config.properties
│   └── test/
│       └── java/
│           └── com/
│               └── Hrup/
│                   └── HotelHelper/
│                       ├── database/
│                       │   ├── KVDatabaseTest.java
│                       │   └── DatabaseManagerTest.java
│                       ├── model/
│                       │   ├── RoomTest.java
│                       │   ├── CustomerTest.java
│                       │   └── ReservationTest.java
│                       ├── service/
│                       │   ├── RoomServiceTest.java
│                       │   ├── CustomerServiceTest.java
│                       │   └── ReservationServiceTest.java
│                       └── util/
│                           ├── ConfigLoaderTest.java
│                           └── LoggerTest.java
├── pom.xml
└── README.md
```

### 结构说明
- **src/main/java**：存放项目的主要业务逻辑代码。
    - **database**：包含数据库相关的类，如 `KVDatabase` 实现键值数据库的基本操作，`DatabaseManager` 负责数据库的管理和维护。
    - **model**：定义酒店相关的数据模型，如 `Room` 表示客房信息，`Customer` 表示客户信息，`Reservation` 表示预订记录。
    - **service**：提供业务服务层的类，如 `RoomService` 处理客房相关的业务逻辑，`CustomerService` 处理客户相关的业务逻辑，`ReservationService` 处理预订相关的业务逻辑。
    - **util**：包含一些工具类，如 `ConfigLoader` 用于加载配置文件，`Logger` 用于日志记录。
- **src/main/resources**：存放项目的配置文件，如 `config.properties` 用于配置数据库连接信息等。
- **src/test/java**：存放项目的测试代码，用于对各个模块进行单元测试。
- **pom.xml**：Maven 项目的配置文件，用于管理项目的依赖和构建信息。
- **README.md**：项目的说明文档，即当前文件。

## 五、安装与配置
### 环境要求
- **JDK**：Java Development Kit 1.8 及以上版本。
- **Maven**：用于项目的依赖管理和构建，建议使用 3.6.x 及以上版本。

### 安装步骤
1. **克隆项目**：从代码仓库克隆项目到本地。
```bash
git clone <项目仓库地址>
```
2. **进入项目目录**：
```bash
cd HotelHelperKV
```
3. **构建项目**：使用 Maven 构建项目，下载项目所需的依赖。
```bash
mvn clean install
```

### 配置步骤
1. **配置文件**：打开 `src/main/resources/config.properties` 文件，根据实际情况配置数据库连接信息、日志级别等。
```properties
# 数据库连接信息
database.host=localhost
database.port=6379
database.password=your_password

# 日志级别
log.level=INFO
```
2. **修改配置**：根据需要修改配置文件中的参数，保存并关闭文件。

## 六、使用方法
### 启动服务
1. **启动数据库服务**：确保数据库服务（如 Redis）已启动，并且配置信息与 `config.properties` 文件中的配置一致。
2. **启动项目**：在 IDE 中运行项目的主类，或者使用以下命令在命令行中启动项目。
```bash
java -jar target/HotelHelperKV-1.0-SNAPSHOT.jar
```

### 功能使用
#### 客房管理
- **添加客房**：调用 `RoomService` 的 `addRoom` 方法，传入客房信息，如房间号、房型、价格等。
- **查询客房**：调用 `RoomService` 的 `getRoomById` 方法，传入客房 ID，获取客房信息。
- **修改客房信息**：调用 `RoomService` 的 `updateRoom` 方法，传入客房 ID 和新的客房信息，更新客房信息。
- **删除客房**：调用 `RoomService` 的 `deleteRoom` 方法，传入客房 ID，删除客房信息。

#### 客户管理
- **添加客户**：调用 `CustomerService` 的 `addCustomer` 方法，传入客户信息，如姓名、联系方式等。
- **查询客户**：调用 `CustomerService` 的 `getCustomerById` 方法，传入客户 ID，获取客户信息。
- **修改客户信息**：调用 `CustomerService` 的 `updateCustomer` 方法，传入客户 ID 和新的客户信息，更新客户信息。
- **删除客户**：调用 `CustomerService` 的 `deleteCustomer` 方法，传入客户 ID，删除客户信息。

#### 预订管理
- **创建预订**：调用 `ReservationService` 的 `createReservation` 方法，传入客户 ID、客房 ID、预订日期等信息，创建预订记录。
- **查询预订**：调用 `ReservationService` 的 `getReservationById` 方法，传入预订 ID，获取预订信息。
- **修改预订信息**：调用 `ReservationService` 的 `updateReservation` 方法，传入预订 ID 和新的预订信息，更新预订信息。
- **取消预订**：调用 `ReservationService` 的 `cancelReservation` 方法，传入预订 ID，取消预订记录。

## 七、代码示例
### 客房添加示例
```java
import com.Hrup.HotelHelper.model.Room;
import com.Hrup.HotelHelper.service.RoomService;

public class RoomAddExample {
    public static void main(String[] args) {
        RoomService roomService = new RoomService();
        Room room = new Room();
        room.setRoomNumber("101");
        room.setRoomType("Standard");
        room.setPrice(200.0);

        roomService.addRoom(room);
        System.out.println("Room added successfully.");
    }
}
```

### 预订创建示例
```java
import com.Hrup.HotelHelper.model.Customer;
import com.Hrup.HotelHelper.model.Room;
import com.Hrup.HotelHelper.model.Reservation;
import com.Hrup.HotelHelper.service.CustomerService;
import com.Hrup.HotelHelper.service.RoomService;
import com.Hrup.HotelHelper.service.ReservationService;

import java.util.Date;

public class ReservationCreateExample {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        RoomService roomService = new RoomService();
        ReservationService reservationService = new ReservationService();

        // 获取客户和客房信息
        Customer customer = customerService.getCustomerById(1);
        Room room = roomService.getRoomById(1);

        // 创建预订记录
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setCheckInDate(new Date());
        reservation.setCheckOutDate(new Date());

        reservationService.createReservation(reservation);
        System.out.println("Reservation created successfully.");
    }
}
```


## 九、常见问题解答
### 1. 数据库连接失败怎么办？
- 检查 `config.properties` 文件中的数据库连接信息是否正确，包括主机名、端口号、密码等。
- 确保数据库服务已启动，并且可以正常访问。

### 2. 项目构建失败怎么办？
- 检查 Maven 配置是否正确，确保 Maven 已正确安装并配置好环境变量。
- 检查 `pom.xml` 文件中的依赖是否正确，尝试使用 `mvn clean install -U` 命令强制更新依赖。

### 3. 如何查看日志信息？
- 日志信息会根据 `config.properties` 文件中配置的日志级别输出到相应的日志文件中，默认日志文件位于项目根目录下的 `logs` 文件夹中。
