package com.example.uno.core.common


open class AppException(
    message: String = "",
    cause: Throwable? = null,
) : Exception(message, cause)

class EmptyCodeException : AppException()

class InvalidCredentialsException(cause: Exception) : AppException(cause = cause)

open class BackendException(
    val code: Int,
    message: String
) : AppException(message)

class ParseBackendResponseException(
    cause: Throwable
) : AppException(cause = cause)

class ConnectionException(cause: Exception) : AppException(cause = cause)

open class RemoteServiceException(
    message: String,
    cause: Exception? = null
) : AppException(message, cause)

class AuthException(cause: Exception? = null) : AppException(cause = cause)

class StorageException(cause: Exception) : AppException(cause = cause)

class UserFriendlyException(
    val userFriendlyMessage: String,
    cause: Exception,
) : AppException(cause.message ?: "", cause)

class NotFoundException : AppException()
