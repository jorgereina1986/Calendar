import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { Events } from '.'

const app = () => express(apiRoot, routes)

let events

beforeEach(async () => {
  events = await Events.create({})
})

test('POST /events 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ title: 'test', date: 'test', description: 'test', time: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.title).toEqual('test')
  expect(body.date).toEqual('test')
  expect(body.description).toEqual('test')
  expect(body.time).toEqual('test')
})

test('GET /events 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body)).toBe(true)
})

test('GET /events/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${events.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(events.id)
})

test('GET /events/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /events/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${events.id}`)
    .send({ title: 'test', date: 'test', description: 'test', time: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(events.id)
  expect(body.title).toEqual('test')
  expect(body.date).toEqual('test')
  expect(body.description).toEqual('test')
  expect(body.time).toEqual('test')
})

test('PUT /events/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ title: 'test', date: 'test', description: 'test', time: 'test' })
  expect(status).toBe(404)
})

test('DELETE /events/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${events.id}`)
  expect(status).toBe(204)
})

test('DELETE /events/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
