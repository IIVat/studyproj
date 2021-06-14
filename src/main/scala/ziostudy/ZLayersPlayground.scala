//package ziostudy
//
//import zio.{ExitCode, Has, Task, ULayer, URIO, ZIO, ZLayer}
//import zio.console._
//import ziostudy.ZLayersPlayground.UserSubscription.UserSubscriptionEnv
//
//import java.io.IOException
//
//object ZLayersPlayground extends zio.App {
//  val userBELayer: ZLayer[Any, Nothing, Has[UserDB.Service] with Has[UserEmailer.Service]] = UserDB.live ++ UserEmailer.live
//  val userSubscriptionLayer: ZLayer[Any, Nothing, UserSubscriptionEnv] = userBELayer >>> UserSubscription.live
//  val illia = User("Illia", "illia@juniqe.com")
//
//  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] = {
//    UserSubscription.subscribe(illia).provideLayer(userSubscriptionLayer).exitCode
//  }
//
//
//  val success = ZIO.succeed(42)
//  val failed = ZIO.fail("Something is wrong")
//
//  val greeting: ZIO[Console, IOException, Unit] = for {
//    _ <- putStrLn("Hi, what's your name")
//    name <- getStrLn
//    _ <- putStrLn(s"Hello, $name!")
//  } yield ()
//
//  case class User(name: String, email: String)
//
//  object UserEmailer {
//    type UserEmailerEnv = Has[UserEmailer.Service]
//
//    trait Service {
//      def notify(user: User, message: String): Task[Unit]
//    }
//
//    val live: ULayer[UserEmailerEnv] = ZLayer.succeed( new Service {
//      override def notify(user: User, message: String): Task[Unit] = Task {
//        println(s"Sending $message to ${user.email}")
//      }
//    })
//
//    def notify(user: User, message: String): ZIO[UserEmailerEnv, Throwable, Unit] = {
//      ZIO.accessM(hasService => hasService.get.notify(user, message))
//    }
//  }
//
//  object UserDB {
//    type UserDBEnv = Has[UserDB.Service]
//    trait Service {
//      def insert(user: User): Task[Unit]
//    }
//
//    val live = ZLayer.succeed(new Service {
//      override def insert(user: User): Task[Unit] = Task {
//        println(s"${user.name} was added to the DB")
//      }
//    })
//
//    def insert(user: User): ZIO[UserDBEnv, Throwable, Unit] = {
//      ZIO.accessM(_.get.insert(user))
//    }
//  }
//
//  object UserSubscription {
//    type UserSubscriptionEnv = Has[UserSubscription.Service]
//
//    class Service(notifier: UserEmailer.Service, db: UserDB.Service) {
//      def subscribe(user: User): Task[User] =  {
//        for {
//          _ <- db.insert(user)
//          _ <- notifier.notify(user, s"${user.name}, welcome to our team!")
//        } yield user
//      }
//    }
//
//    def live: ZLayer[UserEmailer.UserEmailerEnv with UserDB.UserDBEnv, Nothing, UserSubscriptionEnv] = ZLayer.fromServices[UserEmailer.Service, UserDB.Service, UserSubscription.Service] {
//      (userEmailer, userDB) => new Service(userEmailer, userDB)
//    }
//
//    def subscribe(user: User): ZIO[UserSubscriptionEnv, Throwable, User] = ZIO.accessM(_.get.subscribe(user))
//  }
//}
