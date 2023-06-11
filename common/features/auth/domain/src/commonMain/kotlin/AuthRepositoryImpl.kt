
class AuthRepositoryImpl: AuthRepository {
    override suspend fun getTestString(): String = "Test string!"
}