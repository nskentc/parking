# parking allocation system

API documentaton
http://localhost:8080/swagger-ui.html


Mongo DB sample data
====================
DB name: Geo
Collection Name: Location
/* 1 */
{
    "_id" : ObjectId("5cbb3ede696277af6799bff3"),
    "position" : [ 
        0.001, 
        -0.002
    ],
    "uniqueId" : 1
}

/* 2 */
{
    "_id" : ObjectId("5cbb3f14696277af6799c003"),
    "position" : [ 
        1.0, 
        1.0
    ],
    "uniqueId" : 2
}

/* 3 */
{
    "_id" : ObjectId("5cbb3f14696277af6799c005"),
    "position" : [ 
        0.5, 
        0.5
    ],
    "uniqueId" : 3
}

/* 4 */
{
    "_id" : ObjectId("5cbb3f14696277af6799c007"),
    "position" : [ 
        -0.5, 
        -0.5
    ],
    "uniqueId" : 4
}

Create index in mongo
=====================
db.location.ensureIndex( {position: "2d"} )
